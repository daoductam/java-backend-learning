package com.tamdao.spring_security_2.dto;

import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCategoryArticlesRequest {
    @Positive
    private int categoryId;
}
