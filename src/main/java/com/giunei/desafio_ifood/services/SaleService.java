package com.giunei.desafio_ifood.services;

import com.giunei.desafio_ifood.domain.repositories.OrderRepository;
import com.giunei.desafio_ifood.domain.repositories.SalesBagRepository;
import com.giunei.desafio_ifood.domain.sale.Order;
import com.giunei.desafio_ifood.domain.sale.OrderDTO;
import com.giunei.desafio_ifood.domain.sale.SalesBag;
import com.giunei.desafio_ifood.services.aws.AwsSnsService;
import com.giunei.desafio_ifood.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    private final SalesBagRepository salesBagRepository;
    private final OrderRepository orderRepository;
    private final AwsSnsService snsService;

    public SaleService(SalesBagRepository salesBagRepository, OrderRepository orderRepository,
                       AwsSnsService snsService) {
        this.salesBagRepository = salesBagRepository;
        this.orderRepository = orderRepository;
        this.snsService = snsService;
    }

    public Order sell(OrderDTO orderData) {
        SalesBag salesBag = salesBagRepository.save(orderData.salesBag());

        orderData.salesBag().setId(salesBag.getId());
        Order order = new Order(orderData);
        snsService.publish(new MessageDTO(salesBag.toString()));
        orderRepository.save(order);

        snsService.publish(new MessageDTO(order.toString()));
        return order;
    }

    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }

}
