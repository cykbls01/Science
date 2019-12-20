package com.example.demo.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Resources")
public class Resources {
    @Id
    private String id;
    private String UserId;
    private String Time;
    @Indexed
    private String Title;
    private String Abstract;
    private List<String> AuthorName;
    private List<String> AuthorCompany;
    @Indexed
    private List<String> Keyword;
    private String Type;
    private String Location;
    private String certificateId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }



    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAbstract() {
        return Abstract;
    }

    public void setAbstract(String anAbstract) {
        Abstract = anAbstract;
    }

    public List<String> getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(List<String> authorName) {
        AuthorName = authorName;
    }

    public List<String> getAuthorCompany() {
        return AuthorCompany;
    }

    public void setAuthorCompany(List<String> authorCompany) {
        AuthorCompany = authorCompany;
    }

    public List<String> getKeyword() {
        return Keyword;
    }

    public void setKeyword(List<String> keyword) {
        Keyword = keyword;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
