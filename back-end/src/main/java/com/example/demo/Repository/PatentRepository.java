package com.example.demo.Repository;

import com.example.demo.Entity.Patent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatentRepository extends MongoRepository<Patent, String> {
}
