package com.example.demo.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Resources")
public class Resources {
    @Id
    private String Id;
    private String UserId;
    private String Time;
    private Project Project;
    private Passage Passage;
    private Patent Patent;
    private String Title;
    private String Detail;

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public com.example.demo.Entity.Project getProject() {
        return Project;
    }

    public void setProject(com.example.demo.Entity.Project project) {
        Project = project;
    }

    public com.example.demo.Entity.Passage getPassage() {
        return Passage;
    }

    public void setPassage(com.example.demo.Entity.Passage passage) {
        Passage = passage;
    }

    public com.example.demo.Entity.Patent getPatent() {
        return Patent;
    }

    public void setPatent(com.example.demo.Entity.Patent patent) {
        Patent = patent;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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
}
