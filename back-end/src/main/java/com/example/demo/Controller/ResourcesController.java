package com.example.demo.Controller;


import com.example.demo.Entity.Expert;
import com.example.demo.Entity.Resources;
import com.example.demo.Entity.User;
import com.example.demo.Repository.ResourcesRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Util.FollowUtil;
import com.example.demo.Util.ResourcesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/rest",produces = "application/json;charset-utf-8")
public class ResourcesController {
    @Autowired
    private ResourcesRepository resourcesRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;


    @PostMapping("/resources/add")
    public String AddResources(@RequestBody Resources resources, HttpSession session)
    {

        User user=(User)session.getAttribute("user");
        resources.setUserId(user.getId());
        ResourcesUtil.AddResources(resources,resourcesRepository);
        return "success";


    }


    @PostMapping("/resources/delete")
    public String DeleteResources(@RequestBody Resources resources, HttpSession session)
    {

        resources=resourcesRepository.findById(resources.getid()).get();
        User user=(User)session.getAttribute("user");
        if(resources.getUserId().equals(user.getId())==false)
            return "error";
        ResourcesUtil.DeleteResources(resources,resourcesRepository);
        return "success";

    }


    @PostMapping("/resources/find")
    public List<Resources> FindResources(@RequestParam(value = "name") String name, HttpSession session) throws ParseException {

        List<Resources> resourcesList=ResourcesUtil.SearchResources(name,mongoTemplate);
        return resourcesList;

    }

    @PostMapping("/expert/find")
    public List<Expert> FindExpert(@RequestParam(value = "name") String name,HttpSession session) throws ParseException {

        List<Expert> expertList= ResourcesUtil.SearchExpert(name, mongoTemplate);
        return expertList;

    }

    @GetMapping("/resources/recommand")
    public List<Resources> RecommandResources() throws ParseException {

        List<Resources> resourcesList=ResourcesUtil.SearchResources("computer",mongoTemplate);
        return resourcesList;

    }


}
