package com.example.demo.Util;

import com.example.demo.Dao.FollowDao;
import com.example.demo.Entity.Expert;
import com.example.demo.Entity.Follow;

import com.example.demo.Repository.FollowRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.List;

public class FollowUtil {

    //添加关注
    public static void  AddFollow(String followId, String fanId, FollowRepository followRepository)
    {
        Follow follow = new Follow();
        follow.setFanId(fanId);
        follow.setFollowId(followId);
        followRepository.save(follow);
    }

    //取消关注
    public static String deleteFollow(String followId, String fanId, MongoTemplate mongoTemplate,FollowRepository followRepository)
    {
        FollowDao followDao = new FollowDao();
        Follow follow = FollowDao.findFollowByID(followId,fanId,mongoTemplate);
        if(follow == null)
            return "You have not foucus on this person!";
        followRepository.delete(follow);
        return "delete success!";
    }

    //if has focused on
    public static Boolean hasFocus(String followId, String fanId, MongoTemplate mongoTemplate){
        FollowDao followDao = new FollowDao();
        Follow follow = FollowDao.findFollowByID(followId,fanId,mongoTemplate);
        //no
        if(follow == null)
            return false;
        //yes
        return true;
    }

    //see follow list
    public static List<Expert> seeFollowList(String fanId, MongoTemplate mongoTemplate) {
        FollowDao followDao = new FollowDao();
        List<Expert> expertList = FollowDao.findFollowById(fanId, mongoTemplate);
        return expertList;
    }
}
