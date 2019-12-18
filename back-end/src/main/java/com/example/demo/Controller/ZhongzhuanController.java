package com.example.demo.Controller;


import com.example.demo.Entity.Expert;
import com.example.demo.Entity.Resources;
import com.example.demo.Entity.ZhongZhuan;
import com.example.demo.Repository.ExpertRepository;
import com.example.demo.Repository.ResourcesRepository;
import com.example.demo.Repository.ZhongzhuanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ZhongzhuanController {

    @Autowired
    private ZhongzhuanRepo zhongzhuanRepo;
    @Autowired
    private ExpertRepository expertRepository;
    @Autowired
    private ResourcesRepository resourcesRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @ResponseBody
    @GetMapping("/zhongzhuan")
    public String zhongzhuan()
    {
        List<ZhongZhuan> zhongZhuans=zhongzhuanRepo.findAll();


        for(int i=0;i<zhongZhuans.size();i++)
        {
            Expert expert=new Expert();
            expert.setRealName(zhongZhuans.get(i).getName());
            expert.setResourcesNumber(zhongZhuans.get(i).getPapers().size());
            expert=expertRepository.save(expert);

            for(int j=0;j<zhongZhuans.get(i).getPapers().size();j++)
            {
                Resources resources=new Resources();
                resources.setUserId(expert.getId());
                resources.setTitle(zhongZhuans.get(i).getPapers().get(j).getTitle());
                resources.setTime(zhongZhuans.get(i).getPapers().get(j).getYear());
                resourcesRepository.save(resources);

            }


        }

        return "success";

    }


}
