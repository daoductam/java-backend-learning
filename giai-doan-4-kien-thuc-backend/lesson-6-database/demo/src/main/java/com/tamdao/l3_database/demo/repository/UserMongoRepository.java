package com.tamdao.l3_database.demo.repository;

import com.tamdao.l3_database.demo.entity.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMongoRepository extends MongoRepository<UserMongo, String> {
}
