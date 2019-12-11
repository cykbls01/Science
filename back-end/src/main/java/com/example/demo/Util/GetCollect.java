package com.example.demo.Util;

import com.example.demo.Dao.CollectDao;
import com.example.demo.Entity.Collect;
import com.example.demo.Repository.CollectRepository;
import com.example.demo.Repository.ResourcesRepository;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class GetCollect {
    //focus resource
    public static void getCollect(String resourceId, String usrId, CollectRepository collectRepository){
        Collect collect = new Collect();
        collect.setResourcesId(resourceId);
        collect.setUserId(usrId);
        collectRepository.save(collect);
    }

    //see collect list
    public static List<Collect> seeCollectList(String usrId, MongoTemplate mongoTemplate){
        CollectDao collectDao = new CollectDao();
        List<Collect>  collectList = CollectDao.getColloectByUserId(usrId,mongoTemplate);
        if(collectList.size() == 0)
            return null;
        return collectList;
    }

    //cancel collect
    public static String cancelCollectList(String userId,String collectId,MongoTemplate mongoTemplate){
        Collect collect = CollectDao.getCollectByUsrId(userId, collectId, mongoTemplate);
        if(collect == null)
            return "You have not get this collect.";
        return "cancel success.";
    }
}
