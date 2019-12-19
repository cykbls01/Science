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
   public String AddFollow(@RequestParam(value = "follow") String name,HttpSession session)
   {
       User user=(User)session.getAttribute("user");
       Query query=new Query(Criteria.where("Username").is(name));
       User user1=mongoTemplate.findOne(query, User.class);

       Expert expert=expertRepository.findById(user.getExpertId()).get();
       expert.setFollowNumber(expert.getFollowNumber()+1);
       Follow follow = new Follow();
       follow.setFanId(user.getId());
       follow.setFollowId(user1.getId());
       follow.setFanname(user.getUsername());
       follow.setFollowname(user.getUsername());
       followRepository.save(follow);
       return "success";

   }


    @PostMapping("/follow/delete")
    public String DeleteFollow(@RequestParam(value = "follow") String name,HttpSession session)
    {
        User user=(User)session.getAttribute("user");
        Query query=new Query(Criteria.where("Username").is(name));
        User user1=mongoTemplate.findOne(query, User.class);
        Expert expert=expertRepository.findById(user.getExpertId()).get();
        expert.setFollowNumber(expert.getFollowNumber()-1);
        FollowUtil.deleteFollow(user1.getId(),user.getId(),mongoTemplate,followRepository);
        return "success";
    }



    @GetMapping("/follow/getlist")
    public List<Expert> GetFollowList(HttpSession session)
    {
        User user=(User)session.getAttribute("user");
        List<Expert> expertList=FollowUtil.seeFollowList(user.getId(),mongoTemplate);
        return expertList;
    }

    @GetMapping("/follow/isfollow")
    public Boolean IsFollow(@RequestParam(value = "follow") String name,HttpSession session)
    {
        User user=(User)session.getAttribute("user");
        Query query=new Query(Criteria.where("Username").is(name));
        User user1=mongoTemplate.findOne(query, User.class);

        return FollowUtil.hasFocus(user1.getId(),user.getId(),mongoTemplate);
    }




}
