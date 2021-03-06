package com.example.demo.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Follow")
public class Follow {
    @Id
    private String id;
    private String followId;
    private String fanId;
    private String followname;
    private String fanname;

    public String getFollowname() {
        return followname;
    }

    public void setFollowname(String followname) {
        this.followname = followname;
    }

    public String getFanname() {
        return fanname;
    }

    public void setFanname(String fanname) {
        this.fanname = fanname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public String getFanId() {
        return fanId;
    }

    public void setFanId(String fanId) {
        this.fanId = fanId;
    }
}
