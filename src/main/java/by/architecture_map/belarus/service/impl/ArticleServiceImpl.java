package by.architecture_map.belarus.service.impl;

import by.architecture_map.belarus.entity.Article;
import by.architecture_map.belarus.exception.NotFoundException;
import by.architecture_map.belarus.repository.jpa.ArticleRepository;
import by.architecture_map.belarus.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public Article create(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article find(int id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Construction not found with id: " + id));
    }

    @Override
    public List<Article> find(String request) {
        Criteria criteria = new Criteria();
        if (request != null && !request.isEmpty()) {
            criteria = criteria
                    .or("title").contains(request)
                    .or("content").contains(request)
                    .or("shortDescription").contains(request)
                    .or("tag.name").contains(request);
        }
        CriteriaQuery query = new CriteriaQuery(criteria);
        SearchHits<Article> searchHits = elasticsearchOperations.search(query, Article.class);
        return searchHits.stream()
                .map(hit -> hit.getContent())
                .collect(Collectors.toList());
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article update(int id, Article updatedArticle) {
        return applyUpdate(id, existing -> {
            existing.setTitle(updatedArticle.getTitle());
            existing.setContent(updatedArticle.getContent());
            existing.setShortDescription(updatedArticle.getShortDescription());
            existing.setDemonstrativeImage(updatedArticle.getDemonstrativeImage());
            existing.setTag(updatedArticle.getTag());
        });
    }

    @Override
    public Article patchUpdate(int id, Article updatedArticle) {
        return applyUpdate(id, existing -> {
            if (updatedArticle.getTitle() != null && !updatedArticle.getTitle().isEmpty())
                existing.setTitle(updatedArticle.getTitle());
            if (updatedArticle.getContent() != null && !updatedArticle.getContent().isEmpty())
                existing.setContent(updatedArticle.getContent());
            if (updatedArticle.getShortDescription() != null && !updatedArticle.getShortDescription().isEmpty())
                existing.setShortDescription(updatedArticle.getShortDescription());
            if (updatedArticle.getDemonstrativeImage() != null)
                existing.setDemonstrativeImage(updatedArticle.getDemonstrativeImage());
            if (updatedArticle.getTag() != null && !updatedArticle.getTag().isEmpty())
                existing.setTag(updatedArticle.getTag());
        });
    }

    @Override
    public void delete(int id) {
        find(id);
        articleRepository.deleteById(id);
    }

    private Article applyUpdate(int id, Consumer<Article> update) {
        Article existing = find(id);
        update.accept(existing);
        return articleRepository.save(existing);
    }

}
