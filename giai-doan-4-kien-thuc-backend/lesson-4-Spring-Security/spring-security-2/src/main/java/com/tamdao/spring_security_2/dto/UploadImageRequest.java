package com.tamdao.spring_security_2.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UploadImageRequest {
    @Length(min = 1)
    private String base64ImageString;
}
