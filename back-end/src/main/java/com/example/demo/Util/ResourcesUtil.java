package com.example.demo.Util;

import com.example.demo.Entity.*;
import com.example.demo.Repository.ResourcesRepository;
import org.springframework.web.multipart.MultipartFile;

public class ResourcesUtil {



    public static String AddResources(User user, Resources resources, ResourcesRepository resourcesRepository)
    {
        resources.setUserId(user.getId());

        resourcesRepository.save(resources);
        return "success";



    }
    public static Resources AddProject(User user, Resources resources,
                                      MultipartFile multipartFile, Project project)
    {

        project.setName(multipartFile.getName());
        project.setLocation("Users/chenyikun/"+user.getId()+"/resources/");
        FileUtil.WriteFile(project.getLocation(),multipartFile);
        resources.setProject(project);

        return resources;
    }
    public static Resources AddPassage(User user, Resources resources,
                                      MultipartFile multipartFile, Passage passage)
    {

        passage.setName(multipartFile.getName());
        passage.setLocation("Users/chenyikun/"+user.getId()+"/resources/");
        FileUtil.WriteFile(passage.getLocation(),multipartFile);
        resources.setPassage(passage);

        return resources;
    }

    public static Resources AddPatent(User user, Resources resources,
                                       MultipartFile multipartFile, Patent patent)
    {

        patent.setName(multipartFile.getName());
        patent.setLocation("Users/chenyikun/"+user.getId()+"/resources/");
        FileUtil.WriteFile(patent.getLocation(),multipartFile);
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








}
