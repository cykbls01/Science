package com.example.demo.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Check")
public class Check {
    @Id
    private String id;

    private Apply Apply;

    //PASS or REFUSE
    private String Status;

    private String Time;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Apply getApply() {
        return this.Apply;
    }

    public void setApply(Apply apply) {
        this.Apply = apply;
    }

    public String getStatus() {
        return this.Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public String getTime() {
        return this.Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }
}
