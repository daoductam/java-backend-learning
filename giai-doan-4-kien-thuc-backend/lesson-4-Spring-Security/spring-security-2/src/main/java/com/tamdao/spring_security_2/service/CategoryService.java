package com.tamdao.spring_security_2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tamdao.spring_security_2.dto.GetCategoryArticleResponse;
import com.tamdao.spring_security_2.dto.GetCategoryArticlesRequest;
import com.tamdao.spring_security_2.exception.CategoryNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    GetCategoryArticleResponse getArticles(GetCategoryArticlesRequest request) throws CategoryNotFoundException, JsonProcessingException;
}
