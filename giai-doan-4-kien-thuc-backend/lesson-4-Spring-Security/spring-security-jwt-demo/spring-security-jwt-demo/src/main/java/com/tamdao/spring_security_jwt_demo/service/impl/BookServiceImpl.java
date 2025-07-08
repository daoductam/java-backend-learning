package com.tamdao.spring_security_jwt_demo.service.impl;

import com.tamdao.spring_security_jwt_demo.entity.Book;
import com.tamdao.spring_security_jwt_demo.repository.BookRepository;
import com.tamdao.spring_security_jwt_demo.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }
}
