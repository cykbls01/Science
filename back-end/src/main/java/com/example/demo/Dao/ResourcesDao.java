package com.example.demo.Dao;

import com.example.demo.Entity.Resources;
import com.example.demo.Repository.ResourcesRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.util.ArrayList;
import java.util.List;

public class ResourcesDao {
    public static List<Resources> FindByTiTle(String name, MongoTemplate mongoTemplate)
    {
        Query query=new Query(Criteria.where("Title").regex(name));
        query.addCriteria(new Criteria().orOperator(
                Criteria.where("Title").regex(name),
                Criteria.where("Abstract").regex(name),
                Criteria.where("Keyword").regex(name)
        ));
        List<Resources> resourcesList=mongoTemplate.find(query,Resources.class);
        return  resourcesList;
    }







}
