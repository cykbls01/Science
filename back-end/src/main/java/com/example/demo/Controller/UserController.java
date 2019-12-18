package com.example.demo.Controller;


import com.example.demo.Dao.UserDao;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Util.FileUtil;
import com.example.demo.Util.Mail;
import com.example.demo.Util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @ResponseBody
    @PostMapping("/user/upload")
    public String Upload(@RequestParam("file") MultipartFile file, HttpSession session) {
        User user=(User)session.getAttribute("user");
        UserUtil.UploadImage(user,file,userRepository);
        return "success";
    }

    @ResponseBody
    @GetMapping("/user/getImage")
    public byte[] GetImage(HttpSession session) throws Exception {
        User user=(User)session.getAttribute("user");
        byte[] byt=UserUtil.GetImage(user,userRepository);
        return byt;
    }


    @PostMapping("/user/login")
    public String Login(@RequestBody User user,HttpSession session)
    {

        if(UserUtil.Login(user,mongoTemplate).equals("success"))
        {
            user= UserDao.FindUserByName(user.getUsername(),mongoTemplate);
            session.setAttribute("user",user);
            return "success";
        }
        else
        {
            return "error";
        }
    }


    @PostMapping("/user/register")
    public String Register(@RequestBody User user,HttpSession session)
    {

        if(UserUtil.Register(user,userRepository,mongoTemplate).equals("success"))
        {
            user= UserDao.FindUserByName(user.getUsername(),mongoTemplate);
            session.setAttribute("user",user);
            return "success";
        }
        else
        {
            return "error";
        }
    }

    @PostMapping("/user/findpwd")
    public String Findpwd(@RequestParam(value = "email")String email,HttpSession session)
    {
        if(UserUtil.FindPassword(email,mongoTemplate).equals("success"))
        {
            User user=UserDao.FindUserByEmail(email,mongoTemplate);
            String VCode=String.valueOf((int)(1+Math.random()*(100000-10000+1)));
            Mail mail=new Mail();
            mail.sendSimpleMail(user.getEmail(),"密码找回邮件",VCode);
            session.setAttribute("yanzhengma",VCode);
            session.setAttribute("user",user);
            return "success";
        }
        else
        {
            return "error";
        }
    }


    @PostMapping("/user/modifypwd")
    public String Modifypwd(@RequestParam(value = "yanzhengma")String yanzhengma,@RequestParam(value = "newpassword")String pwd,HttpSession session) {
        String VCode = (String) session.getAttribute("yanzhengma");
        User user = (User) session.getAttribute("user");
        if (VCode.equals(yanzhengma))
        {
            user.setPassword(pwd);
            UserUtil.ModifyPassword(user,userRepository);
            return "success";
        }
        else
        {
            return "error";
        }

    }




}
