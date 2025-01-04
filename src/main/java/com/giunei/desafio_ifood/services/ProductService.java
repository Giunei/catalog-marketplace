package com.giunei.desafio_ifood.services;

import com.giunei.desafio_ifood.domain.category.exceptions.CategoryNotFoundException;
import com.giunei.desafio_ifood.domain.product.Product;
import com.giunei.desafio_ifood.domain.product.ProductDTO;
import com.giunei.desafio_ifood.domain.product.exceptions.ProductNotFoundException;
import com.giunei.desafio_ifood.domain.repositories.ProductRepository;
import com.giunei.desafio_ifood.services.aws.AwsSnsService;
import com.giunei.desafio_ifood.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final CategoryService categoryService;
    private final ProductRepository repository;
    private final AwsSnsService snsService;

    public ProductService(ProductRepository repository,
                          CategoryService categoryService,
                          AwsSnsService snsService) {
        this.repository = repository;
        this.categoryService = categoryService;
        this.snsService = snsService;
    }

    public Product insert(ProductDTO productData) {
        categoryService.getById(productData.categoryId()).orElseThrow(CategoryNotFoundException::new);
        Product product = new Product(productData);

        this.repository.save(product);
        this.snsService.publish(new MessageDTO(product.toString()));

        return product;
    }

    public Product update(String id, ProductDTO productData) {
        Product product = this.repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        this.categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        if (!productData.title().isEmpty()) product.setTitle(productData.title());
        if (!productData.description().isEmpty()) product.setDescription(productData.description());
        if (productData.price() != null) product.setPrice(productData.price());
        if (productData.categoryId() != null) product.setCategory(productData.categoryId());

        this.repository.save(product);

        this.snsService.publish(new MessageDTO(product.toString()));

        return product;
    }

    public void delete(String id) {
        Product product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.repository.delete(product);
        this.snsService.publish(new MessageDTO(product.deleteToString()));
    }

    public List<Product> getAll() {
        return this.repository.findAll();
    }
}
