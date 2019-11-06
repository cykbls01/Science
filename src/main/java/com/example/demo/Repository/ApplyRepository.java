package com.example.demo.Repository;
import com.example.demo.Entity.Apply;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface ApplyRepository extends MongoRepository<Apply, String> {
}
