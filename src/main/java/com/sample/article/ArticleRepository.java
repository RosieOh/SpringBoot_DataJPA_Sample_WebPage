package com.sample.article;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByIdGreatingThanOrderByIdDesc(Long id, Pageable paging);
}
