package com.example.demo.Repository;

import com.example.demo.Entity.Resources;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResourcesRepository extends MongoRepository<Resources, String> {
}
