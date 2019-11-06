package com.example.demo.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Comment")
public class Comment {
    @Id
    private String Id;
    private String Content;
    private String UserId;
    private String ResourcesId;
    private String Time;

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getResourcesId() {
        return ResourcesId;
    }

    public void setResourcesId(String resourcesId) {
        ResourcesId = resourcesId;
    }
}
