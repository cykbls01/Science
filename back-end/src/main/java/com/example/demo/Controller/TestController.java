package com.example.demo.Controller;

import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class TestController {
    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping("/test/hello")
    public String hello()
    {



        return "hello";
    }
}
