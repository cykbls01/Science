package com.example.demo.Controller;


import com.example.demo.Dao.ResourcesDao;
import com.example.demo.Entity.Expert;
import com.example.demo.Entity.Resources;
import com.example.demo.Repository.ExpertRepository;
import com.example.demo.Util.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;

@Controller
public class ExpertController {
    @Autowired
    ExpertRepository expertRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/expert/info")
    public Expert GetExpertInfo(@RequestParam(value = "expertId")String id)
    {

        Expert expert=new Expert();
        expert=expertRepository.findById(expert.getId()).get();
        return expert;

    }

    @GetMapping("/expert/resoureces")
    public List<Resources> GetResouerces(@RequestParam(value = "expertId")String id) throws ParseException {
        List<Resources> resources= ResourcesDao.FindByUserid(id,mongoTemplate);
        Time.sort(resources);
        return resources;

    }






}
