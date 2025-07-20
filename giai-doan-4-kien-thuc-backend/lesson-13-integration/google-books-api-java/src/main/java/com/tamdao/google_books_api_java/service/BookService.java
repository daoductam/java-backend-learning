package com.tamdao.google_books_api_java.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tamdao.google_books_api_java.dto.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class BookService {

    @Autowired
    private  RestTemplate restTemplate;

    private final String GOOGLE_BOOKS_API = "https://www.googleapis.com/books/v1/volumes?q=";

    public List<BookResponse> searchBooks(String query) {

        String url =  GOOGLE_BOOKS_API + query;

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        JsonNode items = response.getBody().get("items");

        List<BookResponse> result = new ArrayList<>();

        if (items != null) {
            for (JsonNode item : items) {
                JsonNode volumeInfo = item.get("volumeInfo");

                String title = volumeInfo.path("title").asText("");
                String description = volumeInfo.path("description").asText("");
                String thumbnail = volumeInfo.path("imageLinks").path("thumbnail").asText("");
                String authors = volumeInfo.path("authors").isArray()
                        ? String.join(", ", StreamSupport.stream(volumeInfo.get("authors").spliterator(), false)
                        .map(JsonNode::asText).toList())
                        : "";

                result.add(new BookResponse(title,authors, description, thumbnail));
            }

        }

        return result;
    }
}
