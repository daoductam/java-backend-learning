package com.tamdao.spring_security_2.repository;

import com.tamdao.spring_security_2.entity.Article;
import com.tamdao.spring_security_2.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findOneByName(String name);
}
