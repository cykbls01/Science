package com.example.demo.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Collect")
public class Collect {
    @Id
    private String id;
    private String UserId;
    private String ResourcesId;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return this.UserId;
    }

    public void setUserId(String userId) {
        this.UserId = userId;
    }

    public String getResourcesId() {
        return this.ResourcesId;
    }

    public void setResourcesId(String resourcesId) {
        this.ResourcesId = resourcesId;
    }
}
