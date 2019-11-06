package com.example.demo.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Follow")
public class Follow {
    @Id
    private String id;
    private String FollowId;
    private String FanId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFollowId() {
        return FollowId;
    }

    public void setFollowId(String followId) {
        FollowId = followId;
    }

    public String getFanId() {
        return FanId;
    }

    public void setFanId(String fanId) {
        FanId = fanId;
    }
}
