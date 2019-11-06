package com.example.demo.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Expert")
public class Expert {
    @Id
    private String Id;

    private String RealName;

    private String Company;

    private String PhoneNumber;

    private String Profile;

    private String Education;

    private List<String> Skills;

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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
