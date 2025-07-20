package com.tamdao.google_books_api_java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private String title;
    private String authors;
    private String description;
    private String thumbnail;
}
