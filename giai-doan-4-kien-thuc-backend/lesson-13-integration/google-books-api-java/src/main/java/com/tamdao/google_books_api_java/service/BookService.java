package com.tamdao.google_books_api_java.service;

import com.tamdao.google_books_api_java.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private  RestTemplate restTemplate;

    private final String GOOGLE_BOOKS_API = "https://www.googleapis.com/books/v1/volumes?q=";

    public List<BookResponse> searchBooks(String keyword) {

        String url =  GOOGLE_BOOKS_API + keyword;

        GoogleBooksResponse response = restTemplate.getForObject(url,
                GoogleBooksResponse.class);

        if (response == null || response.items() == null) {
            return List.of();
        }

        return response.items().stream()
                .map(item -> {
                   BookVolumeInfo info = item.volumeInfo();
                   String authors = info.authors() != null ? String.join(", ", info.authors()) : "Unknown";
                   String thumbnail = info.imageLinks() != null ? info.imageLinks().thumbnail() : null;

                   return new BookResponse(
                           info.title(),
                           authors,
                           info.description(),
                           thumbnail
                   );
                })
                .collect(Collectors.toList());
    }
}
