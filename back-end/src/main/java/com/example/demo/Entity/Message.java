package com.example.demo.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Message")
public class Message {
    @Id
    private String id;
    private String SendId;
    private String GetId;
    private String Theme;
    private String Content;
    private String Time;

    public String getTheme() {
        return Theme;
    }

    public void setTheme(String theme) {
        Theme = theme;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSendId() {
        return SendId;
    }

    public void setSendId(String sendId) {
        SendId = sendId;
    }

    public String getGetId() {
        return GetId;
    }

    public void setGetId(String getId) {
        GetId = getId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
