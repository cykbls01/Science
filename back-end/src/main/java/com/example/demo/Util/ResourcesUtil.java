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
    public static Resources AddProject(User user, Resources resources,
                                      MultipartFile multipartFile, Project project)
    {


        project.setLocation("/usr/chenyikun/"+user.getId()+"/resources/");
        FileUtil.WriteFile(project.getLocation(),multipartFile);
        project.setLocation("/usr/chenyikun/"+user.getId()+"/resources/"+multipartFile.getOriginalFilename());
        resources.setProject(project);

        return resources;
    }
    public static Resources AddPassage(User user, Resources resources,
                                      MultipartFile multipartFile, Passage passage)
    {


        passage.setLocation("/usr/chenyikun/"+user.getId()+"/resources/");
        FileUtil.WriteFile(passage.getLocation(),multipartFile);
        passage.setLocation("/usr/chenyikun/"+user.getId()+"/resources/"+multipartFile.getOriginalFilename());
        resources.setPassage(passage);

        return resources;
    }

    public static Resources AddPatent(User user, Resources resources,
                                       MultipartFile multipartFile, Patent patent)
    {


        patent.setLocation("/usr/chenyikun/"+user.getId()+"/resources/");
        FileUtil.WriteFile(patent.getLocation(),multipartFile);
        patent.setLocation("/usr/chenyikun/"+user.getId()+"/resources/"+multipartFile.getOriginalFilename());
        resources.setPatent(patent);

        return resources;
    }

    public static String DeleteResources(Resources resources,ResourcesRepository resourcesRepository)
    {
        resources=resourcesRepository.findById(resources.getId()).get();
        if(resources.getProject()!=null)
        {
            FileUtil.DeleteFile(resources.getProject().getLocation());
        }
        if(resources.getPassage()!=null)
        {
            FileUtil.DeleteFile(resources.getPassage().getLocation());
        }
        if(resources.getPatent()!=null)
        {
            FileUtil.DeleteFile(resources.getPatent().getLocation());
        }
        resourcesRepository.delete(resources);
        return "success";
    }

    public static Resources DeleteProject(Resources resources)
    {
        FileUtil.DeleteFile(resources.getProject().getLocation());
        resources.setProject(null);
        return resources;
    }
    public static Resources DeletePassage(Resources resources)
    {
        FileUtil.DeleteFile(resources.getPassage().getLocation());
        resources.setPassage(null);
        return resources;
    }
    public static Resources DeletePatent(Resources resources)
    {
        FileUtil.DeleteFile(resources.getPatent().getLocation());
        resources.setPatent(null);
        return resources;
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
