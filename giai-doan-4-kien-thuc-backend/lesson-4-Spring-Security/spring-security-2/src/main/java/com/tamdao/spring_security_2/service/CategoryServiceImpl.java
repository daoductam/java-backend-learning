package com.tamdao.spring_security_2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tamdao.spring_security_2.dto.GetCategoryArticleResponse;
import com.tamdao.spring_security_2.dto.GetCategoryArticlesRequest;
import com.tamdao.spring_security_2.entity.Category;
import com.tamdao.spring_security_2.exception.CategoryNotFoundException;
import com.tamdao.spring_security_2.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{
    private static final int CACHE_TIME_IN_MINUTE = 5;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public GetCategoryArticleResponse getArticles(GetCategoryArticlesRequest request)
            throws CategoryNotFoundException, JsonProcessingException {

        String cacheKey =   String.format("category:%d:articles",
                request.getCategoryId());
        String cachedData = redisTemplate.opsForValue().get(cacheKey);
        GetCategoryArticleResponse response;
        if (Objects.isNull(cachedData)) {
            log.info("cache miss, get from mysql");
            Optional<Category> optionalCategory = categoryRepository.findById(request.getCategoryId());
            if (optionalCategory.isEmpty()) {
                throw new CategoryNotFoundException();
            }
            Category category = optionalCategory.get();
            response = GetCategoryArticleResponse.builder()
                    .articles(category.getArticles())
                    .build();
            redisTemplate.opsForValue().set(cacheKey,
                    objectMapper.writeValueAsString(response)
                    , Duration.ofMinutes(CACHE_TIME_IN_MINUTE));
        } else  {
            log.info("cache hit");
            response = objectMapper.readValue(cachedData,
                    GetCategoryArticleResponse.class);
        }
        return response;
    }
}
