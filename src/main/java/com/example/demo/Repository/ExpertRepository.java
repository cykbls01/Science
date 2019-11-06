package com.example.demo.Repository;
import com.example.demo.Entity.Expert;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExpertRepository extends MongoRepository<Expert, String>{
}
