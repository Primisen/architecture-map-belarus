package by.architecture_map.belarus.service;

import by.architecture_map.belarus.entity.Article;

import java.util.List;

public interface ArticleService {

    Article create(Article article);

    Article find(int id);

    /**
     * Using for finding Articles in Elasticsearch
     */
    List<Article> find(String request);

    List<Article> findAll();

    Article update(int id, Article updatedArticle);

    Article patchUpdate(int id, Article updatedArticle);

    void delete(int id);

}
