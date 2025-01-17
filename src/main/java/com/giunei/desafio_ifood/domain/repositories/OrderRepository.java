package com.giunei.desafio_ifood.domain.repositories;

import com.giunei.desafio_ifood.domain.sale.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
