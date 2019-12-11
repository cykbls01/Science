package com.example.demo.Util;

import com.example.demo.Dao.CheckDao;
import com.example.demo.Entity.Apply;
import com.example.demo.Entity.Check;
import com.example.demo.Entity.User;
import com.example.demo.Repository.CheckRepository;
import org.springframework.data.mongodb.core.MongoTemplate;

public class CheckUnit {
    //pass
    public static void passCheck(Apply apply, String expertId, CheckRepository checkRepository){
        //add check
        Time time = new Time();
        Check check = new Check();
        check.setApply(apply);
        check.setStatus("PASS");
        check.setTime(time.toString());
        checkRepository.save(check);
        //connect user with expert
        User user = new User();
        user.setExpertId(expertId);
    }

    //refuse
    public static void refuseCheck(Apply apply,CheckRepository checkRepository){
        Time time = new Time();
        Check check = new Check();
        check.setTime(time.toString());
        check.setApply(apply);
        check.setStatus("REFUSE");
    }

    //getback
    public static String getApplyCheck(String checkId, CheckRepository checkRepository, MongoTemplate mongoTemplate){
        Check check = CheckDao.getCheckByCheckId(checkId,mongoTemplate);
        if(check == null)
            return "There is no check entity.";
        checkRepository.delete(check);
        return "remove check success";
    }
}
