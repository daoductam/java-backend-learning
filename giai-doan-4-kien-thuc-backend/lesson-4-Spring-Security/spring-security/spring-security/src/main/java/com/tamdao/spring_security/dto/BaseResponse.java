package com.tamdao.spring_security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
