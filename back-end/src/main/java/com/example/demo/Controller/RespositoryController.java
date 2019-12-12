package com.example.demo.Controller;


import com.example.demo.Entity.Resources;
import com.example.demo.Entity.User;
import com.example.demo.Repository.ResourcesRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Util.FollowUtil;
import com.example.demo.Util.ResourcesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RespositoryController {
    @Autowired
    private ResourcesRepository resourcesRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @ResponseBody
    @GetMapping("/resources/add")
    public String AddResources(HttpSession session)
    {
        Resources resources=new Resources();
        resources.setType("Passage");
        resources.setTitle("ceshi1");

        User user=(User)session.getAttribute("user");
        resources.setUserId(user.getId());
        ResourcesUtil.AddResources(resources,resourcesRepository);
        return "success";


    }

    @ResponseBody
    @GetMapping("/resources/delete")
    public String DeleteResources(HttpSession session)
    {
        Resources resources=new Resources();
        resources.setId("5df1fe2b5d34e81057952847");
        System.out.println(resources.getId());
        resources=resourcesRepository.findById(resources.getId()).get();

        User user=(User)session.getAttribute("user");
        if(resources.getUserId().equals(user.getId())==false)
            return "error";
        ResourcesUtil.DeleteResources(resources,resourcesRepository);
        return "success";


    }
}
