package com.tamdao.spring_security_jwt_demo.controller;

import com.tamdao.spring_security_jwt_demo.entity.Book;
import com.tamdao.spring_security_jwt_demo.service.BookService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> getAll() {
        return bookService.findAll();
    }

    @PostMapping
    @RolesAllowed("BOOK_ADMIN")
    public Book create(@RequestBody Book book) {
        return bookService.create(book);
    }
}
