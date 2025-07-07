package com.tamdao.spring_security_2.controller;

import com.tamdao.spring_security_2.dto.UploadImageRequest;
import com.tamdao.spring_security_2.dto.UploadImageResponse;
import com.tamdao.spring_security_2.service.UploadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/upload")
public class UploadController {
    @Autowired
    UploadService uploadService;

    @PostMapping()
    public ResponseEntity<UploadImageResponse> postMethodName(@Valid @RequestBody UploadImageRequest request) {
        String url = uploadService.uploadImage(request.getBase64ImageString());
        return ResponseEntity.status(HttpStatus.OK).body(UploadImageResponse.builder().url(url).build());
    }

    @GetMapping("/user")
    public Object getPrincipal(Authentication authentication) {
        return authentication.getPrincipal();
    }
}
