package com.example.demo.Repository;

import com.example.demo.Entity.Collect;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CollectRepository extends MongoRepository<Collect, String> {
}
