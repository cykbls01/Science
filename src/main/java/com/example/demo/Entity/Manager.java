package com.example.demo.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Manager")
public class Manager {
    @Id
    private String Id;

    private String UserName;

    private String Password;





    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsername() {
        return UserName;
    }

    public void setUsername(String username) {
        UserName = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}


