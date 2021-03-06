package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/rest",produces = "application/json;charset-utf-8")
public class HelloController {
    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello()
    {



        return "hello";
    }
}
