package com.tamdao.spring_security.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class BaseResponse<T> {

    @Builder.Default
    private int code = 1000 ;
    private String message;
    private T data;
}
