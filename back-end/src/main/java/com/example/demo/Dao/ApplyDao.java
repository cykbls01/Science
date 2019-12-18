package com.example.demo.Dao;

import com.example.demo.Entity.Apply;
import com.example.demo.Entity.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


public class ApplyDao {
    public static Apply findApplyByUserId(String userId, MongoTemplate mongoTemplate){
        Query query = new Query(Criteria.where("UserId").is(userId));
        return mongoTemplate.findOne(query,Apply.class);
    }

    public static User findUserByApplyId(String applyId,MongoTemplate mongoTemplate){
        Query query = new Query(Criteria.where("id").is(applyId));
        Apply apply = mongoTemplate.findOne(query,Apply.class);
        Query query1 = new Query((Criteria.where("id").is(apply.getUserId())));
        return mongoTemplate.findOne(query1,User.class);
    }
}
