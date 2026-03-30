package by.architecture_map.belarus.service.impl;

import by.architecture_map.belarus.entity.Article;
import by.architecture_map.belarus.entity.Image;
import by.architecture_map.belarus.exception.NotFoundException;
import by.architecture_map.belarus.repository.jpa.ArticleRepository;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private ElasticsearchOperations elasticsearchOperations;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Test
    void whenCreateArticle_thenSaveArticle() {
        // given
        Image image = new Image();
        image.setUrl("http://..");
        Article article = new Article();
        article.setTitle("Title");
        article.setContent("Really beautiful");
        article.setShortDescription("Short description");
        article.setDemonstrativeImage(image);
        article.setId(1);
        when(articleRepository.save(article)).thenReturn(article);

        // when
        Article result = articleService.create(article);

        // then
        verify(articleRepository, times(1)).save(article);
        assertEquals(article, result);
    }

    @Test
    void whenFindAllArticles_thenReturnListOfArticles() {
        // given
        Image image = new Image();
        image.setUrl("http://..");

        Article article1 = new Article();
        article1.setTitle("Title");
        article1.setContent("Really beautiful");
        article1.setShortDescription("Short description");
        article1.setDemonstrativeImage(image);
        article1.setId(1);

        Article article2 = new Article();
        article2.setTitle("Title");
        article2.setContent("Beautiful");
        article2.setShortDescription("Short description");
        article2.setDemonstrativeImage(image);
        article2.setId(2);

        List<Article> articles = Arrays.asList(article1, article2);
        when(articleRepository.findAll()).thenReturn(articles);

        // when
        List<Article> result = articleService.findAll();

        // then
        verify(articleRepository, times(1)).findAll();
        assertEquals(articles, result);
    }

    @Test
    void whenFind_thenReturnArticle() {
        // given
        int id = 1;
        Image image = new Image();
        image.setUrl("http://..");
        Article article = new Article();
        article.setTitle("Title");
        article.setContent("Really beautiful");
        article.setShortDescription("Short description");
        article.setDemonstrativeImage(image);
        article.setId(id);
        when(articleRepository.findById(id)).thenReturn(Optional.of(article));

        // when
        Article result = articleService.find(id);

        // then
        verify(articleRepository, times(1)).findById(id);
        assertEquals(article, result);
    }

    @Test
    void whenUpdate_thenUpdateArticle() {
        // given
        int id = 1;
        Image image = new Image();
        image.setUrl("http://..");

        Article existingArticle = new Article();
        existingArticle.setTitle("Title");
        existingArticle.setContent("Really beautiful");
        existingArticle.setShortDescription("Short description");
        existingArticle.setDemonstrativeImage(image);
        existingArticle.setId(id);

        Article updatedArticle = new Article();
        updatedArticle.setTitle("Title");
        updatedArticle.setContent("Beautiful");
        updatedArticle.setShortDescription("Short description");
        updatedArticle.setDemonstrativeImage(image);
        updatedArticle.setId(id);

        when(articleRepository.findById(id)).thenReturn(Optional.of(existingArticle));
        when(articleRepository.save(existingArticle)).thenAnswer(invocation -> {
            existingArticle.setTitle(updatedArticle.getTitle());
            existingArticle.setContent(updatedArticle.getContent());
            existingArticle.setShortDescription(updatedArticle.getShortDescription());
            existingArticle.setDemonstrativeImage(updatedArticle.getDemonstrativeImage());
            existingArticle.setTag(updatedArticle.getTag());
            return existingArticle;
        });

        // when
        Article result = articleService.update(id, updatedArticle);

        // then
        verify(articleRepository, times(1)).findById(id);
        verify(articleRepository, times(1)).save(existingArticle);
        assertEquals(existingArticle, result);
    }

    @Test
    void whenDelete_thenDeleteArticle() {
        // given
        int id = 1;
        Article article = mock(Article.class);
        when(articleRepository.findById(id)).thenReturn(Optional.of(article));
        doNothing().when(articleRepository).deleteById(id);

        // when
        articleService.delete(id);

        // then
        verify(articleRepository, times(1)).findById(id);
        verify(articleRepository, times(1)).deleteById(id);
    }

    @Test
    void whenDeleteArticleAndArticleDoesNotExists_thenThrowNotFoundException() {
        // given
        int id = 1;
        when(articleRepository.findById(id)).thenReturn(Optional.empty());

        // when & then
        assertThrows(NotFoundException.class, () -> articleService.delete(id));

        // verify
        verify(articleRepository, times(1)).findById(id);
        verify(articleRepository, never()).deleteById(id);
    }

}