package com.tamdao.spring_security_2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Types;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class User implements UserDetails {
    @Id
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @OneToMany
    private Set<Authority> authorities;

    private String password;

    private String name;

    private String picture;

    @Column(unique = true)
    private String username;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialNonExpired;

    private String provider;

    private String providerId;

    private boolean enabled;
}
