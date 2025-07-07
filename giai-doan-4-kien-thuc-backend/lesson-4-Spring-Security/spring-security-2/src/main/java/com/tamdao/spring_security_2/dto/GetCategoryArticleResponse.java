package com.tamdao.spring_security_2.dto;

import com.tamdao.spring_security_2.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCategoryArticleResponse {
    private List<Article> articles;
}
