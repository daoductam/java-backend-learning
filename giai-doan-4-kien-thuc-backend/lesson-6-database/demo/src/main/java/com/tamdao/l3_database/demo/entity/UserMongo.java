package com.tamdao.l3_database.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Document(collection = "users" )
@Getter
@Setter
@NoArgsConstructor
public class UserMongo {
    @Id
    private String id;

    private String name;

    private String email;

    private String phone;

    private LocalDate dob;
}
