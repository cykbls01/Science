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
    public String AddResources(@RequestBody Resources resources)
    {

        User user=userRepository.findById(resources.getCertificateId()).get();
        resources.setUserId(user.getExpertId());
        ResourcesUtil.AddResources(resources,resourcesRepository);
        return "success";


    }


    @PostMapping("/resources/delete")
    public String DeleteResources(@RequestBody Resources resources)
    {

        resources=resourcesRepository.findById(resources.getid()).get();
        if(resources.getUserId().equals(resources.getCertificateId())==false)
            return "error";
        ResourcesUtil.DeleteResources(resources,resourcesRepository);
        return "success";

    }


    @PostMapping("/resources/find")
    public List<Resources> FindResources(@RequestParam(value = "name") String name) throws ParseException {

        List<Resources> resourcesList=ResourcesUtil.SearchResources(name,mongoTemplate);

        return resourcesList;

    }

    @PostMapping("/resources/findInResources")
    public List<Resources> FindResourcesInResult(@RequestParam(value = "name") String name, @RequestParam(value = "result") List<Resources> baoliu) throws ParseException {

        List<Resources> newlist=baoliu;
        newlist.clear();
        for(int i=0;i<baoliu.size();i++) {
            if(baoliu.get(i).getTitle().contains(name)||baoliu.get(i).getAbstract().contains(name))
                newlist.add(baoliu.get(i));
        }
        return newlist;

    }
    @PostMapping("/resources/findInExpert")
    public List<Expert> FindExpertInResult(@RequestParam(value = "name") String name, @RequestParam(value = "result") List<Expert> baoliu) throws ParseException {

        List<Expert> newlist=baoliu;
        newlist.clear();
        for(int i=0;i<baoliu.size();i++) {
            if(baoliu.get(i).getRealName().contains(name)||baoliu.get(i).getCompany().contains(name))
                newlist.add(baoliu.get(i));
        }
        return newlist;

    }

    @PostMapping("/expert/findById")
    public Resources FindById(@RequestParam(value = "id") String id) throws ParseException {


        return resourcesRepository.findById(id).get();

    }

    @PostMapping("/expert/find")
    public List<Expert> FindExpert(@RequestParam(value = "name") String name) throws ParseException {

        List<Expert> expertList = ResourcesUtil.SearchExpert(name, mongoTemplate);
        return expertList;

    }
    @ResponseBody
    @GetMapping("/resources/recommand")
    public List<Resources> RecommandResources() throws ParseException {

        List<Resources> resourcesList=ResourcesUtil.SearchResources("人工智能",mongoTemplate);
        if(resourcesList.size()>=8)
        return resourcesList.subList(0,7);
        else
            return resourcesList;

    }


}
