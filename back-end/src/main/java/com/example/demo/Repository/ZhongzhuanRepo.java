package com.example.demo.Repository;


import com.example.demo.Entity.ZhongZhuan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ZhongzhuanRepo extends MongoRepository<ZhongZhuan, String> {
}
