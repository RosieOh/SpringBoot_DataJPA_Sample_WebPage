package com.sample.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    public List<Article> getArticles(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return articleRepository.findByIdGreatingThanOrderByIdDesc(0l, pageable);
    }

    public Article insertArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article getArticle(Long id) {
        return articleRepository.findById(id).get();
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    public Article updateArticle(Long id, Article updatedArticle) {
        Article article = articleRepository.findById(id).get();
        article.update(updatedArticle);
        articleRepository.save(article);

        return article;
    }
}
