package com.example.demo.Dao;

import com.example.demo.Entity.Collect;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class CollectDao {
    public static List<Collect> getColloectByUserId(String userId, MongoTemplate mongoTemplate){
        Query query = new Query(Criteria.where("UserId").is(userId));
        return mongoTemplate.find(query,Collect.class);
    }

    public static Collect getCollectByUsrId(String userId,String collectId ,MongoTemplate mongoTemplate){
        Query query = new Query(Criteria.where("UserId").is(userId));
        List<Collect> collectList = mongoTemplate.find(query,Collect.class);
        for(int i = 0; i < collectList.size(); i++){
            if(collectList.get(i).getId().equals(collectId))
                return collectList.get(i);
        }
        return null;
    }

}
