package com.example.demo.Controller;


import com.example.demo.Dao.ResourcesDao;
import com.example.demo.Entity.Expert;
import com.example.demo.Entity.Resources;
import com.example.demo.Repository.ExpertRepository;
import com.example.demo.Util.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value="/rest",produces = "application/json;charset-utf-8")
public class ExpertController {
    @Autowired
    ExpertRepository expertRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping("/expert/info")
    public Expert GetExpertInfo(@RequestParam(value = "id")String id)
    {

        Expert expert=new Expert();
        expert=expertRepository.findById(id).get();
        return expert;

    }

    @PostMapping("/expert/resources")
    public List<Resources> GetResouerces(@RequestParam(value = "id")String id) throws ParseException {
        List<Resources> resources= ResourcesDao.FindByUserid(id,mongoTemplate);
        Time.sort(resources);
        return resources;

    }






}
