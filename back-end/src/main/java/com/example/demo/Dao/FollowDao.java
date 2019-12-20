package com.example.demo.Dao;

import com.example.demo.Entity.Expert;
import com.example.demo.Entity.Follow;
import com.example.demo.Repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;

public class FollowDao {
    public static Follow findFollowByID(String followId, String fanId, MongoTemplate mongoTemplate) {
        Query query = new Query(Criteria.where("followId").is(followId));
        List<Follow> followList = mongoTemplate.find(query, Follow.class);
        for(int i = 0 ; i < followList.size() ; i++) {
            if (followList.get(i).getFanId().equals(fanId))
                return followList.get(i);
        }
        return null;
    }

    public static List<Follow> findFollowByID(String fanId, MongoTemplate mongoTemplate){
        Query query = new Query(Criteria.where("fanId").is(fanId));
        List<Follow> followList = mongoTemplate.find(query,Follow.class);
        if(followList.size() == 0)
            return null;
        return followList;
    }

    public static List<Expert> findFollowById(String fanId,MongoTemplate mongoTemplate){
        Query query = new Query(Criteria.where("fanId").is(fanId));
        List<Follow> followList = mongoTemplate.find(query,Follow.class);
        List<Expert> expertList = null;
        for(int i = 0; i < followList.size(); i++){
            Query query1 = new Query(Criteria.where("id").is(followList.get(i).getFollowId()));
            expertList.add(mongoTemplate.findOne(query1,Expert.class));
        }
        return expertList;
    }
}
