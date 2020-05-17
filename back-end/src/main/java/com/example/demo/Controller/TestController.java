package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Util.FileUtil;
import com.example.demo.Util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;


@RestController

public class TestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @ResponseBody
    @RequestMapping("/test/login")
    public String Login()
    {
        User user=new User();
        user.setUsername("cyk");
        user.setPassword("cyk");
        return UserUtil.Login(user,mongoTemplate);
    }

    @ResponseBody
    @RequestMapping("/test/register")
    public String Register()
    {
        User user=new User();
        user.setUsername("cyk");
        user.setPassword("cyk");
        return UserUtil.Register(user,userRepository,mongoTemplate);
    }
    @ResponseBody
    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file) {
        FileUtil.WriteFile("D:\\usr\\chenyikun\\",file);
       // FileUtil.WriteFile("./usr/chenyikun/",file);


        return "success";
    }
    @ResponseBody
    @GetMapping("/download")
    public Object downloadModel(){
        String path="/usr/chenyikun/";
        ResponseEntity<InputStreamResource> response = null;
        try {
            response = FileUtil.ReadFile(path+"你好.jpeg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @ResponseBody
    @GetMapping(value = "/file/image", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] image() throws Exception {


        byte[] byt = FileUtil.GetImage("D:/usr/chenyikun/你好.jpg");
        return byt;
    }



}
