package com.example.demo.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Collect")
public class Collect {
    @Id
    private String Id;
    private String UserId;
    private String ResourcesId;

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

    public String getResourcesId() {
        return ResourcesId;
    }

    public void setResourcesId(String resourcesId) {
        ResourcesId = resourcesId;
    }
}
