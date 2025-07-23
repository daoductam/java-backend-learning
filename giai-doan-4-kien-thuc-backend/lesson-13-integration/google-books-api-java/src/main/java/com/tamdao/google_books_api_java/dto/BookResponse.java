package com.tamdao.google_books_api_java.dto;

public record BookResponse(
        String title,
        String authors,
        String description,
        String thumbnail
) {
}
