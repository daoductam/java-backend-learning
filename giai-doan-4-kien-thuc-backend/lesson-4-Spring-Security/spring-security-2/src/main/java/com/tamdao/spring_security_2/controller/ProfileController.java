package com.tamdao.spring_security_2.controller;

import com.tamdao.spring_security_2.dto.profile.GetProfileResponse;
import com.tamdao.spring_security_2.entity.Profile;
import com.tamdao.spring_security_2.service.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/profiles")
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<GetProfileResponse> getProfile(@PathVariable int id) {
        Profile profile = profileService.getProfile(id);
        return ResponseEntity.ok(GetProfileResponse.builder().profile(profile).build());
    }
}
