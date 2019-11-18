package com.example.demo.Controller;


import com.example.demo.Dao.UserDao;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Util.FileUtil;
import com.example.demo.Util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;


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

    @ResponseBody
    @PostMapping("/user/login")
    public String Login(@RequestBody User user,HttpSession session)
    {
        if(UserUtil.Login(user,userRepository).equals("success"))
        {
            user= UserDao.FindUserByName(user.getUsername(),userRepository);
            session.setAttribute("user",user);
            return "success";
        }
        else
        {
            return "error";
        }
    }

    @ResponseBody
    @PostMapping("/user/register")
    public String Register(@RequestBody User user,HttpSession session)
    {
        if(UserUtil.Register(user,userRepository).equals("success"))
        {
            user= UserDao.FindUserByName(user.getUsername(),userRepository);
            session.setAttribute("user",user);
            return "success";
        }
        else
        {
            return "error";
        }
    }




}
