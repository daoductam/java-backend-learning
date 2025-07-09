package com.tamdao.l3_database.demo.service;

import com.tamdao.l3_database.demo.entity.UserMongo;
import com.tamdao.l3_database.demo.repository.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMongoService {

    @Autowired
    private UserMongoRepository userMongoRepository;

    public UserMongo createUser(UserMongo userMongo) {

        return userMongoRepository.save(userMongo);
    }
}
