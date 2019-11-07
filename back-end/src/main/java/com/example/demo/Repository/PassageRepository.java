package com.example.demo.Repository;

import com.example.demo.Entity.Passage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PassageRepository extends MongoRepository<Passage, String> {
}
