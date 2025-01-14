package com.giunei.desafio_ifood.domain.repositories;

import com.giunei.desafio_ifood.domain.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.SplittableRandom;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
