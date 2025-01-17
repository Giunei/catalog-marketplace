package com.giunei.desafio_ifood.domain.repositories;

import com.giunei.desafio_ifood.domain.sale.SalesBag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesBagRepository extends MongoRepository<SalesBag, String> {
}
