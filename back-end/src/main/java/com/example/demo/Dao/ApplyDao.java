package com.example.demo.Dao;

import com.example.demo.Entity.Apply;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


public class ApplyDao {
    public static Apply findApplyByUserId(String userId, MongoTemplate mongoTemplate){
        Query query = new Query(Criteria.where("UserId").is(userId));
        return mongoTemplate.findOne(query,Apply.class);
    }
}
