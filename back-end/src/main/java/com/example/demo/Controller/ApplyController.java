package com.example.demo.Controller;


import com.example.demo.Entity.Apply;
import com.example.demo.Entity.Expert;
import com.example.demo.Entity.Resources;
import com.example.demo.Entity.User;
import com.example.demo.Repository.ApplyRepository;
import com.example.demo.Repository.ExpertRepository;
import com.example.demo.Repository.UserRepository;
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

@RestController
@RequestMapping(value="/rest",produces = "application/json;charset-utf-8")
public class ApplyController {
    @Autowired
    private ApplyRepository applyRepository;
    @Autowired
    private ExpertRepository expertRepository;
@Autowired
private MongoTemplate mongoTemplate;
@Autowired
private UserRepository userRepository;
    @PostMapping("/apply/add")
    public String AddApply(@RequestBody Expert expert)
    {
        Apply apply=new Apply();
        apply.setContent(expert);
        apply.setTime(Time.getTime());
        apply.setUserId(expert.getCertificateId());
        apply.setStatus("check");
        applyRepository.save(apply);
        return "success";

    }

    @PostMapping("/apply/delete")
    public String DeleteApply(@RequestParam(value = "applyid") String applyid)
    {
        applyRepository.deleteById(applyid);
        return "success";

    }

    @PostMapping("/apply/getById")
    public Apply GetApply(@RequestParam(value = "applyid") String applyid)
    {
        Apply apply=new Apply();
        apply=applyRepository.findById(applyid).get();
        return apply;

    }
    @GetMapping("/apply/getAll")
    public List<Apply> GetApply()
    {
        List<Apply> apply;
        apply=applyRepository.findAll();
        return apply;

    }
    @PostMapping("/apply/getByUserid")
    public Apply GetApplyByuserid(@RequestParam(value = "certificateId")String userid)
    {

        Apply apply=new Apply();
        Query query=new Query(Criteria.where("UserId").is(userid));
        apply=mongoTemplate.findOne(query,Apply.class);
        return apply;
    }

    @PostMapping("/apply/check")
    public String CheckApply(@RequestParam(value = "applyid") String applyid,@RequestParam(value = "result") String result,@RequestParam(value = "reason") String reason)
    {
        Apply apply=new Apply();
        apply=applyRepository.findById(applyid).get();
        apply.setStatus(result);
        apply.setReason(reason);
        User user=userRepository.findById(apply.getUserId()).get();
        Expert expert=apply.getContent();
        expert=expertRepository.save(expert);


        if(result=="1") {
            user.setExpertId(expert.getId());
            userRepository.save(user);

        }
        applyRepository.delete(apply);

        return  "success";
    }






}
