package com.example.demo.Util;

import com.example.demo.Dao.UserDao;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

public class UserUtil {

    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    public static String Register(User user,UserRepository userRepository, MongoTemplate mongoTemplate)//注册用户，如果用户名和邮箱重复，则报错，否则密码加密后注册
    {

        if(UserDao.FindUserByName(user.getUsername(),mongoTemplate)!=null)
        {

            return "error";
        }
        else if(UserDao.FindUserByEmail(user.getEmail(),mongoTemplate)!=null)
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

    public static String Login(User user, MongoTemplate mongoTemplate)//登录，如果用户名找不到报错，验证密码是否正确，返回相应信息
    {
        User user1=UserDao.FindUserByName(user.getUsername(),mongoTemplate);
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

    public static String ModifyUser(User user,UserRepository userRepository,MongoTemplate mongoTemplate)//修改个人信息，先查看用户名和邮箱是否重复，然后完成修改
    {
        User user1=userRepository.findById(user.getId()).get();
        /*if(user1.getUsername().equals(user.getUsername())==false&&UserDao.FindUserByName(user.getUsername(),mongoTemplate)!=null)
            return "error";
        else if(user1.getEmail().equals(user.getEmail())==false&&UserDao.FindUserByEmail(user.getEmail(),mongoTemplate)!=null)
            return "error";
        else
        {*/
            user.setId(user1.getId());
            userRepository.save(user);
            return "success";
        //}
    }

    public static String ModifyPassword(User user,UserRepository userRepository)//修改密码
    {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "true";
    }

    public static String FindPassword(String email,MongoTemplate mongoTemplate)//找回密码，发送验证邮件，验证邮箱是否存在，然后发送找回邮件
    {
        User user1=UserDao.FindUserByEmail(email,mongoTemplate);
        if(user1==null)
        {
            return "error";
        }
        else
        {
            return "success";
        }



    }

    public static String UploadImage(User user, MultipartFile file,UserRepository userRepository)
    {
        user=userRepository.findById(user.getId()).get();
        //FileUtil.DeleteFile("/usr/chenyikun/"+user.getId()+"/image/"+user.getImageName());
        user.setImageName(file.getOriginalFilename());
        userRepository.save(user);
        FileUtil.WriteFile("/usr/chenyikun/"+user.getId()+"/image/",file);
        return "success";

    }

    public static byte[] GetImage(User user,UserRepository userRepository) throws Exception {


        user=userRepository.findById(user.getId()).get();
        return FileUtil.GetImage("/usr/chenyikun/"+user.getId()+"/image/"+user.getImageName());


    }



}
