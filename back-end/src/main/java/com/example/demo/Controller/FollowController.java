package com.example.demo.Controller;


import com.example.demo.Entity.Apply;
import com.example.demo.Entity.Expert;
import com.example.demo.Entity.Follow;
import com.example.demo.Entity.User;
import com.example.demo.Repository.ExpertRepository;
import com.example.demo.Repository.FollowRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Util.FollowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/rest",produces = "application/json;charset-utf-8")
public class FollowController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpertRepository expertRepository;
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private MongoTemplate mongoTemplate;


   @PostMapping("/follow/add")
   public String AddFollow(@RequestParam(value = "follow") String fid,@RequestParam(value = "certificateId")String userid)
   {

       //Query query=new Query(Criteria.where("RealName").is(name));
       //Expert expert=mongoTemplate.findOne(query, Expert.class);
       Expert expert=expertRepository.findById(fid).get();
       User user=userRepository.findById(userid).get();
       expert.setFollowNumber(expert.getFollowNumber()+1);
       expertRepository.save(expert);
       Follow follow = new Follow();
       follow.setFanId(user.getId());
       follow.setFollowId(expert.getId());
       follow.setFanname(user.getUsername());
       follow.setFollowname(expert.getRealName());
       followRepository.save(follow);
       return "success";

   }


    @PostMapping("/follow/delete")
    public String DeleteFollow(@RequestParam(value = "follow") String fid,@RequestParam(value = "certificateId")String userid)
    {
        User user=userRepository.findById(userid).get();
        //Query query=new Query(Criteria.where("RealName").is(name));
        //Expert expert=mongoTemplate.findOne(query, Expert.class);
        Expert expert=expertRepository.findById(fid).get();
        expert.setFollowNumber(expert.getFollowNumber()-1);
        expertRepository.save(expert);
        FollowUtil.deleteFollow(expert.getId(),user.getId(),mongoTemplate,followRepository);
        return "success";
    }



    @PostMapping("/follow/getlist")
    public List<Expert> GetFollowList(@RequestParam(value = "certificateId")String userid)
    {
        User user=userRepository.findById(userid).get();
        List<Expert> expertList=new ArrayList<Expert>();
        Query query = new Query(Criteria.where("fanId").is(userid));
        List<Follow> followList = mongoTemplate.find(query,Follow.class);
        for(int i=0;i<followList.size();i++)
            expertList.add(expertRepository.findById(followList.get(i).getId()).get());
        return expertList;
    }

    @PostMapping("/follow/isfollow")//通过用户关注
    public Boolean IsFollow(@RequestParam(value = "follow") String name,@RequestParam(value = "certificateId")String userid)
    {
        User user=userRepository.findById(userid).get();
        Query query=new Query(Criteria.where("RealName").is(name));
        Expert expert=mongoTemplate.findOne(query, Expert.class);

        return FollowUtil.hasFocus(expert.getId(),user.getId(),mongoTemplate);
    }
    @PostMapping("/expert/isfollow")//通过专家关注
    public Boolean isFollow(@RequestParam(value = "follow") String id,@RequestParam(value = "certificateId")String userid)
    {
        User user=userRepository.findById(userid).get();
        return FollowUtil.hasFocus(id,user.getId(),mongoTemplate);
    }




}
