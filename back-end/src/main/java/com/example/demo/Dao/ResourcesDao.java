package com.example.demo.Dao;

import com.example.demo.Entity.Resources;
import com.example.demo.Repository.ResourcesRepository;

import java.util.ArrayList;
import java.util.List;

public class ResourcesDao {
    public static List<Resources> FindByName(String name, ResourcesRepository resourcesRepository)
    {
        List<Resources> resourcesList=resourcesRepository.findAll();
        List<Resources> resourcesList1=new ArrayList<Resources>();
        for(int i=0;resourcesList.get(i)!=null;i++)
        {
            if(resourcesList.get(i).getTitle().contains(name))
            {
                resourcesList1.add(resourcesList.get(i));
            }
        }

        return  resourcesList1;

    }





}
