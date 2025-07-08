package com.tamdao.spring_security_jwt_demo.repository;

import com.tamdao.spring_security_jwt_demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
