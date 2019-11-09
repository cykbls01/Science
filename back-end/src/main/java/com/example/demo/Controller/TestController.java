package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {
    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping("/test/login")
    public String Login()
    {
        User user=new User();
        user.setUsername("cyk");
        user.setPassword("cyk");
        return UserUtil.Login(user,userRepository);
    }

    @ResponseBody
    @RequestMapping("/test/register")
    public String Register()
    {
        User user=new User();
        user.setUsername("cyk");
        user.setPassword("cyk");
        return UserUtil.Register(user,userRepository);
    }
}
