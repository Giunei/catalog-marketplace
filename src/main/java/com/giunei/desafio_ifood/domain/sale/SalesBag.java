package com.giunei.desafio_ifood.domain.sale;

import com.giunei.desafio_ifood.domain.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "salesBags")
@Data
@NoArgsConstructor
public class SalesBag {

    private String id;
    private Integer quantity;
    private Product product;
    private String ownerId;

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("quantity", quantity);
        json.put("product", product.getId());
        json.put("type", "sales-bag");

        return json.toString();
    }
}
