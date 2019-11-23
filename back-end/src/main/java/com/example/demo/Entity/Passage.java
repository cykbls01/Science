package com.example.demo.Entity;


import java.util.List;

public class Passage {

    private String Title;
    private String Abstract;
    private List<String> AuthorName;
    private List<String> AuthorCompany;
    private List<String> Keyword;
    private String Time;
    private String Location;


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

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
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


}
