package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Util.FileUtil;
import com.example.demo.Util.ResourcesUtil;
import com.example.demo.Util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;


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
    @ResponseBody
    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file) {
        FileUtil.WriteFile("/Users/chenyikun/",file);



        return "success";
    }
    @ResponseBody
    @GetMapping("/download")
    public Object downloadModel(){
        String path="/Users/chenyikun/";
        ResponseEntity<InputStreamResource> response = null;
        try {
            response = FileUtil.ReadFile(path+"123.jpeg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @ResponseBody
    @GetMapping(value = "/file/image", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] image() throws Exception {


        byte[] byt = FileUtil.GetImage("/Users/chenyikun/123.jpeg");
        return byt;
    }



}
