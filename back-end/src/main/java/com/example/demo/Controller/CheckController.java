package com.example.demo.Controller;

import com.example.demo.Entity.Apply;
import com.example.demo.Entity.User;
import com.example.demo.Repository.ApplyRepository;
import com.example.demo.Repository.CheckRepository;
import com.example.demo.Repository.FollowRepository;
import com.example.demo.Util.ApplyUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CheckController {
    @Autowired
    private CheckRepository checkRepository;
    @Autowired
    private ApplyRepository applyRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @ResponseBody
    @PostMapping("/apply/submit")
    public String submitApply(@RequestBody Apply apply,HttpSession session){
        User user = (User) session.getAttribute("user");
        boolean result = ApplyUnit.submitApply(user.getId(),apply,applyRepository,mongoTemplate);
        if(result)
            return "submit success!";
        return "sumit false";
    }

    @ResponseBody
    @PostMapping("/apply/remove")
    public String removeApply(HttpSession session){
        User user = (User) session.getAttribute("user");
        String result = ApplyUnit.removeApply(user.getId(),mongoTemplate,applyRepository);
        return result;
    }

    @ResponseBody
    @PostMapping("/check/pass")
    public String passCheck(@RequestBody String id,String id){

    }

    @ResponseBody
    @PostMapping("/check/refuse")
    public String refuseCheck(){

    }

    @ResponseBody
    @PostMapping("/check/getback")
    public String getBackCheck(){

    }
}
