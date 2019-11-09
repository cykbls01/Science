package com.example.demo.Util;

import com.example.demo.Dao.UserDao;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserUtil {

    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    public static String Register(User user, UserRepository userRepository)
    {

        if(UserDao.FindUserByName(user.getUsername(),userRepository)!=null)
        {
            return "error";
        }
        else if(UserDao.FindUserByEmail(user.getEmail(),userRepository)!=null)
        {
            return "error";
        }
        else
        {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "success";
        }


    }

    public static String Login(User user,UserRepository userRepository)
    {
        User user1=UserDao.FindUserByName(user.getUsername(),userRepository);
        if(user1==null)
        {
            return "error";

        }
        else
        {

            if(bCryptPasswordEncoder.matches(user.getPassword(),user1.getPassword())==true)
                return  "success";
            else
                return "error";

        }
    }

    public static String ModifyUser(User user,UserRepository userRepository)
    {
        User user1=userRepository.findById(user.getId()).get();
        if(user1.getUsername().equals(user.getUsername())==false&&UserDao.FindUserByName(user.getUsername(),userRepository)!=null)
            return "error";
        else if(user1.getEmail().equals(user.getEmail())==false&&UserDao.FindUserByEmail(user.getEmail(),userRepository)!=null)
            return "error";
        else
        {
            user.setId(user1.getId());
            userRepository.save(user);
            return "success";
        }
    }

    public static String ModifyPassword(User user,UserRepository userRepository)
    {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "true";
    }

    public static String FindPassword(String email,UserRepository userRepository)
    {
        User user1=UserDao.FindUserByEmail(email,userRepository);
        if(user1==null)
        {
            return "error";
        }
        else
        {
            String VCode=String.valueOf((int)(1+Math.random()*(100000-10000+1)));
            Mail mail=new Mail();
            mail.sendSimpleMail(user1.getEmail(),"密码找回邮件",VCode);
            return "success";


        }



    }



}
