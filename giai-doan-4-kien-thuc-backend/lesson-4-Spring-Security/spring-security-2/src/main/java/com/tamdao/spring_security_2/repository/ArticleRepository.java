package com.tamdao.spring_security_2.repository;

import com.tamdao.spring_security_2.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository  extends JpaRepository<Article, Integer> {
}
