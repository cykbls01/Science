package com.example.demo.Util;

import com.example.demo.Dao.ResourcesDao;
import com.example.demo.Entity.*;
import com.example.demo.Repository.ResourcesRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

public class ResourcesUtil {


    public static String AddResources(Resources resources, ResourcesRepository resourcesRepository)
    {

        resources.setTime(Time.getTime());
        resourcesRepository.save(resources);
        return "success";
    }

    public static String AddResources(Resources resources, Expert expert,ResourcesRepository resourcesRepository,MultipartFile multipartFile)
    {
        FileUtil.WriteFile("/usr/chenyikun/"+expert.getId()+"/",multipartFile);
        resources.setTime(Time.getTime());
        resources.setLocation("/usr/chenyikun/"+expert.getId()+"/"+multipartFile.getOriginalFilename());
        resourcesRepository.save(resources);
        return "success";
    }
    public static String DeleteResources(Resources resources,ResourcesRepository resourcesRepository)
    {
        resources=resourcesRepository.findById(resources.getId()).get();
        if(resources.getLocation()!=null)
        FileUtil.DeleteFile(resources.getLocation());
        resourcesRepository.delete(resources);
        return "success";
    }
    public static String ModifyResources(Resources resources,ResourcesRepository resourcesRepository)
    {
        resources.setTime(Time.getTime());
        resourcesRepository.save(resources);
        return "success";
    }

    public static List<Resources> SearchResources(String name, MongoTemplate mongoTemplate) throws ParseException {
        List<Resources> resourcesList= ResourcesDao.FindByTiTle(name,mongoTemplate);
        resourcesList=Time.sort(resourcesList);
        return resourcesList;
    }








}
