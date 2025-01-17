package com.giunei.desafio_ifood.domain.sale;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "orders")
@Data
@NoArgsConstructor
public class Order {

    private String id;
    private String salesBag;
    private Instant date;

    public Order(OrderDTO orderDTO) {
        this.salesBag = orderDTO.salesBag().getId();
        this.date = orderDTO.date();
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("salesBag", salesBag);
        json.put("date", date);
        json.put("type", "order");

        return json.toString();
    }
}
