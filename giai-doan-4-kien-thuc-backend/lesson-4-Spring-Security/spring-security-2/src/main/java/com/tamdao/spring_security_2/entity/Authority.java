package com.tamdao.spring_security_2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.security.core.GrantedAuthority;

import java.sql.Types;
import java.util.UUID;

@Data
@Entity
public class Authority implements GrantedAuthority {
    @Id
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    public String authority;
}
