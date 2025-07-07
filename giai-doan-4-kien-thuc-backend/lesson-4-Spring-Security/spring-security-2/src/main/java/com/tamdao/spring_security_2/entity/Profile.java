package com.tamdao.spring_security_2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    int id;
    String profileImageUrl;
    String displayName;
    String username;
    String bio;
}
