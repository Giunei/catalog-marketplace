package com.giunei.desafio_ifood.domain.repositories;

import com.giunei.desafio_ifood.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
