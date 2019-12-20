package com.example.demo.Controller;


import com.example.demo.Dao.UserDao;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Util.FileUtil;
import com.example.demo.Util.Mail;
import com.example.demo.Util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value="/rest",produces = "application/json;charset-utf-8")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;



    @PostMapping("/user/upload")
    public String Upload(@RequestParam("file") MultipartFile file,@RequestParam(value = "certificateId")String userid) {
        User user=userRepository.findById(userid).get();
        UserUtil.UploadImage(user,file,userRepository);
        return "success";
    }
    @PostMapping("/user/upload1")
    public String Upload1(@RequestParam("file") MultipartFile file,@RequestParam(value = "certificateId")String userid) {
        User user=userRepository.findById(userid).get();
        UserUtil.UploadImage(user,file,userRepository);
        return "success";
    }



    @ResponseBody
    @GetMapping(value="/user/getImage/{id}" ,produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] GetImage(@PathVariable String id) throws Exception {
        User user=userRepository.findById(id).get();
        byte[] byt=UserUtil.GetImage(user,userRepository);
        return byt;
    }


    @PostMapping("/user/login")
    public User Login(@RequestBody User user)
    {

        if(UserUtil.Login(user,mongoTemplate).equals("success"))
        {
            user= UserDao.FindUserByName(user.getUsername(),mongoTemplate);

            return user;
        }
        else
        {
            return null;
        }
    }


    @PostMapping("/user/register")
    public User Register(@RequestBody User user)
    {

        if(UserUtil.Register(user,userRepository,mongoTemplate).equals("success"))
        {
            user= UserDao.FindUserByName(user.getUsername(),mongoTemplate);

            return user;
        }
        else
        {
            return null;
        }
    }

    @PostMapping("/user/modify")
    public User ModifyUser(@RequestBody User user)
    {

        if(UserUtil.ModifyUser(user,userRepository,mongoTemplate).equals("success"))
        {
            user=userRepository.findById(user.getId()).get();

            return user;
        }
        else
        {
            return null;
        }
    }

    @PostMapping("/user/findpwd")
    public String Findpwd(@RequestParam(value = "email")String email)
    {
        if(UserUtil.FindPassword(email,mongoTemplate).equals("success"))
        {
            User user=UserDao.FindUserByEmail(email,mongoTemplate);
            String VCode=String.valueOf((int)(1+Math.random()*(100000-10000+1)));
            Mail mail=new Mail();
            mail.sendSimpleMail(user.getEmail(),"密码找回邮件",VCode);

            return VCode+" "+user.getId();
        }
        else
        {
            return "error";
        }
    }

    @PostMapping("/user/findbyid")
    public User FindbyId(@RequestParam(value = "id")String id)
    {
        return userRepository.findById(id).get();
    }

    @PostMapping("/user/findbyname")
    public User FindbyName(@RequestParam(value = "name")String name)
    {
        return UserDao.FindUserByName(name,mongoTemplate);
    }


    @PostMapping("/user/modifypwd")
    public String Modifypwd(@RequestParam(value = "newpassword")String pwd,@RequestParam(value = "certificateId")String userid) {

        User user = userRepository.findById(userid).get();

            user.setPassword(pwd);
            UserUtil.ModifyPassword(user,userRepository);
            return "success";
    }




}
