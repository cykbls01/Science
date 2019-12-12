package com.example.demo.Controller;


import com.example.demo.Entity.User;
import com.example.demo.Repository.FollowRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Util.FollowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class FollowController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

   @ResponseBody
   @GetMapping("/follow/add")
   public String AddFollow(HttpSession session)
   {
       User user=(User)session.getAttribute("user");
       FollowUtil.AddFollow("5df1f7ade1e04508da4a3b98",user.getId(),followRepository);
       return "success";


   }

    @ResponseBody
    @GetMapping("/follow/delete")
    public String DeleteFollow(HttpSession session)
    {
        User user=(User)session.getAttribute("user");
        FollowUtil.deleteFollow("5df1f7ade1e04508da4a3b98",user.getId(),mongoTemplate,followRepository);
        return "success";


    }


}
