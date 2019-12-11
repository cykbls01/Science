package com.example.demo.Dao;

import com.example.demo.Entity.Check;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


public class CheckDao {
    public static Check getCheckByCheckId(String checkId, MongoTemplate mongoTemplate){
        Query query = new Query(Criteria.where("Id").is(checkId));
        return mongoTemplate.findOne(query,Check.class);
    }
}
