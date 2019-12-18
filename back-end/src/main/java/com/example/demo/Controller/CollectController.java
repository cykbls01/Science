package com.example.demo.Controller;

import com.example.demo.Entity.Collect;
import com.example.demo.Entity.User;
import com.example.demo.Repository.CollectRepository;
import com.example.demo.Repository.FollowRepository;
import com.example.demo.Util.GetCollect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.Repository.CheckRepository;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CollectController {
    @Autowired
    private CheckRepository checkRepository;
    @Autowired
    private CollectRepository collectRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @ResponseBody
    @PostMapping("/collect/get")
    public String getCollect(@RequestBody String id, HttpSession session){
        User user = (User) session.getAttribute("user");
        GetCollect.getCollect(id,user.getId(),collectRepository);
        return "get collect success";
    }

    @ResponseBody
    @PostMapping("/collect/see")
    public List<Collect> seeCollect(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Collect> collectList = GetCollect.seeCollectList(user.getId(),mongoTemplate);
        return collectList;
    }

    @ResponseBody
    @PostMapping("/collect/cancel")
    public String cancelCollect(@RequestBody String id, HttpSession session){
        User user = (User) session.getAttribute("user");
        String result = GetCollect.cancelCollectList(user.getId(),id,mongoTemplate);
        return result;
    }

}
