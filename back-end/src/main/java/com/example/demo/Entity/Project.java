package com.example.demo.Entity;


import java.util.List;

public class Project {


    private String BeginTime;
    private String EndTime;
    private String Title;
    private String Content;
    private List<String> Author;
    private String Location;


    public List<String> getAuthor() {
        return Author;
    }

    public void setAuthor(List<String> author) {
        Author = author;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }


    public String getBeginTime() {
        return BeginTime;
    }

    public void setBeginTime(String beginTime) {
        BeginTime = beginTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
