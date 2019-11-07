package com.example.demo.Repository;

import com.example.demo.Entity.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ManagerRepository extends MongoRepository<Manager, String> {
}
