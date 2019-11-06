package com.example.demo.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Check")
public class Check {
    @Id
    private String Id;

    private Apply Apply;

    private String Status;

    private String Time;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Apply getApply() {
        return Apply;
    }

    public void setApply(Apply apply) {
        Apply = apply;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
