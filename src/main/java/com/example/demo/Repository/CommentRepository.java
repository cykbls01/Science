package com.example.demo.Repository;

import com.example.demo.Entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
