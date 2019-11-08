package com.example.demo.Dao;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserDao {


    public static User FindUserByName(String name,UserRepository userRepository)
    {
        List<User> users= userRepository.findAll();
        for(int i=0;i<users.size();i++)
        {
            if(users.get(i).getUsername().equals(name)==true)
                return users.get(i);

        }
        return null;
    }

    public static User FindUserByEmail(String email,UserRepository userRepository)
    {
        List<User> users= userRepository.findAll();
        for(int i=0;i<users.size();i++)
        {
            if(users.get(i).getEmail().equals(email)==true)
                return users.get(i);

        }
        return null;
    }






}
