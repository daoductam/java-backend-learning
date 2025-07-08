package com.tamdao.spring_security_jwt_demo.service;

import com.tamdao.spring_security_jwt_demo.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book create(Book book);
}
