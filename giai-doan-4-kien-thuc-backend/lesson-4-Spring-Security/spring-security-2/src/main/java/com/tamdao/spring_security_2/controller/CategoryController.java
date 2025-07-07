package com.tamdao.spring_security_2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tamdao.spring_security_2.dto.GetCategoryArticleResponse;
import com.tamdao.spring_security_2.dto.GetCategoryArticlesRequest;
import com.tamdao.spring_security_2.dto.UserPrincipal;
import com.tamdao.spring_security_2.exception.CategoryNotFoundException;
import com.tamdao.spring_security_2.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;

@Slf4j
@RestController
@RequestMapping(path = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}/articles")
    public ResponseEntity<GetCategoryArticleResponse> getArticles(@PathVariable Integer id, Principal principal)
            throws IOException {

        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) principal;
        log.info("request id={}, auth={}", id, oAuth2AuthenticationToken.getPrincipal().getName());
        UserPrincipal userPrincipal = (UserPrincipal) oAuth2AuthenticationToken.getPrincipal();
        log.info("request id={}, auth={}",id, userPrincipal.getId());
        GetCategoryArticleResponse  response ;
        try {
            response = categoryService.getArticles(GetCategoryArticlesRequest.builder().categoryId(id).build());
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }
 }
