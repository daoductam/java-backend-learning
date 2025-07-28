package com.tamdao.l3_database.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String name;

    private String email;

    private String phone;

    private LocalDate dob;
}
