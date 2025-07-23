package com.tamdao.google_books_api_java.dto;

import java.util.List;

public record BookVolumeInfo(
        String title,
        List<String> authors,
        String description,
        ImageLinks imageLinks
) { }

