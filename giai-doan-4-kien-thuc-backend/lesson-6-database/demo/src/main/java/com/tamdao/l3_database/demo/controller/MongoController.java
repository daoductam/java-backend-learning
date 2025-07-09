package com.tamdao.l3_database.demo.controller;

import com.tamdao.l3_database.demo.entity.UserMongo;
import com.tamdao.l3_database.demo.service.UserMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class MongoController {


    @Autowired
    private UserMongoService userMongoService;

    @PostMapping("/users")
    public UserMongo createUser(@RequestBody UserMongo request) {
        return userMongoService.createUser(request);
    }



}
