package com.example.demo.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Resources")
public class Resources {
    @Id
    private String Id;
    private String UserId;
    private String Time;
    private String PassageId;
    private String ProjectId;
    private String PatentId;

    public String getPassageId() {
        return PassageId;
    }

    public void setPassageId(String passageId) {
        PassageId = passageId;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String projectId) {
        ProjectId = projectId;
    }

    public String getPatentId() {
        return PatentId;
    }

    public void setPatentId(String patentId) {
        PatentId = patentId;
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
