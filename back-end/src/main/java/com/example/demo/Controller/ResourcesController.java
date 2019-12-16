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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ResourcesController {
    @Autowired
    private ResourcesRepository resourcesRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @ResponseBody
    @PostMapping("/resources/add")
    public String AddResources(@RequestBody Resources resources, HttpSession session)
    {


        User user=(User)session.getAttribute("user");
        resources.setUserId(user.getId());
        ResourcesUtil.AddResources(resources,resourcesRepository);
        return "success";


    }

    @ResponseBody
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

    @ResponseBody
    @PostMapping("/resources/find")
    public List<Resources> FindResources(@RequestBody String name, HttpSession session) throws ParseException {

        List<Resources> resourcesList=ResourcesUtil.SearchResources(name,mongoTemplate);
        return resourcesList;

    }
    @ResponseBody
    @PostMapping("/expert/find")
    public List<Expert> FindExpert(@RequestBody String name, HttpSession session) throws ParseException {

        List<Expert> expertList= ResourcesUtil.SearchExpert(name, mongoTemplate);
        return expertList;

    }


}
