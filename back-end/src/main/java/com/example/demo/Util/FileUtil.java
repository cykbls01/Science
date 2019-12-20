package com.example.demo.Util;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    public static String WriteFile(String location,MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            File file1=new File(location+file.getOriginalFilename());

            if(!file1.exists()){
                file1.createNewFile();
            }

            Path path = Paths.get(location+file.getOriginalFilename());
            Files.write(path, bytes);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    public static ResponseEntity<InputStreamResource> ReadFile(String location)
    {
        ResponseEntity<InputStreamResource> response = null;
        try {
            File file=new File(location);
            InputStream inputStream = new FileInputStream(location);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Content-Disposition",
                    "attachment; filename="
                            + new String(file.getName().getBytes("gbk"), "iso8859-1") );
            headers.add("Expires", "0");
            response = ResponseEntity.ok().headers(headers)
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(inputStream));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return response;
    }

    public static byte[] GetImage(String location) throws Exception {

        File file = new File(location);
        InputStream input = new FileInputStream(file);
        byte[] byt = new byte[input.available()];
        input.read(byt);
        return byt;
    }

    public static String DeleteFile(String location)
    {
        File file=new File(location);
        file.delete();
        return "success";
    }
}
