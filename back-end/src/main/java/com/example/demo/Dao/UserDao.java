package com.example.demo.Dao;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserDao {



    public static User FindUserByName(String name, MongoTemplate mongoTemplate)
    {
        Query query=new Query(Criteria.where("Username").is(name));
        return mongoTemplate.findOne(query,User.class);
    }

    public static User FindUserByEmail(String email, MongoTemplate mongoTemplate)
    {
        Query query=new Query(Criteria.where("Email").is(email));
        return mongoTemplate.findOne(query,User.class);
    }







}
