package com.giunei.desafio_ifood.domain.controllers;

import com.giunei.desafio_ifood.domain.product.Product;
import com.giunei.desafio_ifood.domain.product.ProductDTO;
import com.giunei.desafio_ifood.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody ProductDTO productData) {
        Product product = this.productService.insert(productData);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = this.productService.getAll();
        return ResponseEntity.ok().body(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDTO productData) {
        Product product = this.productService.update(id, productData);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") String id) {
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
