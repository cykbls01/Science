package com.example.demo.Controller;


import com.example.demo.Entity.Apply;
import com.example.demo.Entity.Expert;
import com.example.demo.Entity.Resources;
import com.example.demo.Entity.User;
import com.example.demo.Repository.ApplyRepository;
import com.example.demo.Util.Time;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ApplyController {
    @Autowired
    private ApplyRepository applyRepository;
@Autowired
private MongoTemplate mongoTemplate;

    @PostMapping("/apply/add")
    public String AddApply(@RequestBody Expert expert, HttpSession httpSession)
    {
        Apply apply=new Apply();
        apply.setContent(expert);
        apply.setTime(Time.getTime());
        User user=(User)httpSession.getAttribute("user");
        apply.setUserId(user.getId());
        apply.setStatus("check");
        applyRepository.save(apply);
        return "success";

    }

    @PostMapping("/apply/delete")
    public String DeleteApply(@RequestParam(value = "applyid") String applyid, HttpSession httpSession)
    {
        applyRepository.deleteById(applyid);
        return "success";

    }

    @PostMapping("/apply/getById")
    public Apply GetApply(@RequestParam(value = "applyid") String applyid, HttpSession httpSession)
    {
        Apply apply=new Apply();
        apply=applyRepository.findById(applyid).get();
        return apply;

    }
    @PostMapping("/apply/getAll")
    public List<Apply> GetApply()
    {
        List<Apply> apply;
        apply=applyRepository.findAll();
        return apply;

    }
    @PostMapping("/apply/getByUserid")
    public Apply GetApply(HttpSession httpSession)
    {
        User user=(User)httpSession.getAttribute("user");
        Apply apply=new Apply();
        Query query=new Query(Criteria.where("UserId").is(user.getId()));
        apply=mongoTemplate.findOne(query,Apply.class);
        return  apply;
    }

    @PostMapping("/apply/check")
    public String CheckApply(@RequestParam(value = "applyid") String applyid,@RequestParam(value = "result") String result,@RequestParam(value = "reason") String reason,HttpSession httpSession)
    {
        Apply apply=new Apply();
        apply=applyRepository.findById(applyid).get();
        apply.setStatus(result);
        apply.setReason(reason);
        applyRepository.save(apply);
        return  "success";
    }






}
