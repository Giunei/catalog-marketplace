package com.giunei.desafio_ifood.domain.controllers;

import com.giunei.desafio_ifood.domain.sale.Order;
import com.giunei.desafio_ifood.domain.sale.OrderDTO;
import com.giunei.desafio_ifood.services.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sale")
public class SaleController {

    private SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<Order> sell(@RequestBody OrderDTO orderData) {
        Order order = this.saleService.sell(orderData);
        return ResponseEntity.ok().body(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAll() {
        List<Order> orders = this.saleService.getAll();
        return ResponseEntity.ok().body(orders);
    }
}
