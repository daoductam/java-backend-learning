package com.tamdao.google_books_api_java.controller;

import com.tamdao.google_books_api_java.dto.BookResponse;
import com.tamdao.google_books_api_java.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    public BookService bookService;

    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> searchBooks(@RequestParam String q) {
        List<BookResponse> books = bookService.searchBooks(q);
        return ResponseEntity.ok(books);
    }
}
