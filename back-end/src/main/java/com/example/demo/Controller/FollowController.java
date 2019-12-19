package com.example.demo.Controller;


import com.example.demo.Entity.Expert;
import com.example.demo.Entity.User;
import com.example.demo.Repository.ExpertRepository;
import com.example.demo.Repository.FollowRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Util.FollowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
   public String AddFollow(@RequestParam(value = "follow") String id,HttpSession session)
   {
       User user=(User)session.getAttribute("user");
       User user1=userRepository.findById(id).get();
       Expert expert=expertRepository.findById(user.getExpertId()).get();
       expert.setFollowNumber(expert.getFollowNumber()+1);
       FollowUtil.AddFollow(id,user.getId(),followRepository);
       return "success";

   }


    @PostMapping("/follow/delete")
    public String DeleteFollow(@RequestParam(value = "follow") String id,HttpSession session)
    {
        User user=(User)session.getAttribute("user");
        User user1=userRepository.findById(id).get();
        Expert expert=expertRepository.findById(user.getExpertId()).get();
        expert.setFollowNumber(expert.getFollowNumber()-1);
        FollowUtil.deleteFollow(id,user.getId(),mongoTemplate,followRepository);
        return "success";
    }



    @GetMapping("/follow/getlist")
    public List<Expert> GetFollowList(HttpSession session)
    {
        User user=(User)session.getAttribute("user");
        List<Expert> expertList=FollowUtil.seeFollowList(user.getId(),mongoTemplate);
        return expertList;
    }




}
