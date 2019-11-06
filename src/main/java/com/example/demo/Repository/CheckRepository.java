package com.example.demo.Repository;

import com.example.demo.Entity.Check;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CheckRepository extends MongoRepository<Check, String> {
}
