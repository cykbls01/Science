package com.example.demo.Util;

import com.example.demo.Dao.ApplyDao;
import com.example.demo.Entity.Apply;
import com.example.demo.Repository.ApplyRepository;
import org.springframework.data.mongodb.core.MongoTemplate;


public class ApplyUnit {
    public static Boolean submitApply(String userId, Apply apply, ApplyRepository applyRepository, MongoTemplate mongoTemplate){
        Apply apply1 = ApplyDao.findApplyByUserId(userId,mongoTemplate);
        if(apply1 != null)//has submit apply
            return false;
        applyRepository.save(apply);
        return true;
    }

    //remove
    public  static String removeApply(String userId,MongoTemplate mongoTemplate,ApplyRepository applyRepository){
        Apply apply = ApplyDao.findApplyByUserId(userId,mongoTemplate);
        if(apply == null)
            return "You have not submit any apply";
        applyRepository.delete(apply);
        return "remove success";
    }
}
