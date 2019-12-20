package com.example.demo.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Expert")
public class Expert {
    @Id
    private String id;

    private String RealName;

    private String Company;

    private String PhoneNumber;

    private String Profile;

    private String Education;
    private int FollowNumber;
    private int ResourcesNumber=1;
    private String certificateId;

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public int getFollowNumber() {
        return FollowNumber;
    }

    public void setFollowNumber(int followNumber) {
        FollowNumber = followNumber;
    }

    public int getResourcesNumber() {
        return ResourcesNumber;
    }

    public void setResourcesNumber(int resourcesNumber) {
        ResourcesNumber = resourcesNumber;
    }

    private List<String> Skills;

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getProfile() {
        return Profile;
    }

    public void setProfile(String profile) {
        Profile = profile;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public List<String> getSkills() {
        return Skills;
    }

    public void setSkills(List<String> skills) {
        Skills = skills;
    }
}
