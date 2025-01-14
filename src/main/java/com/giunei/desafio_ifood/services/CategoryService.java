package com.giunei.desafio_ifood.services;

import com.giunei.desafio_ifood.domain.category.Category;
import com.giunei.desafio_ifood.domain.category.CategoryDTO;
import com.giunei.desafio_ifood.domain.category.exceptions.CategoryNotFoundException;
import com.giunei.desafio_ifood.domain.repositories.CategoryRepository;
import com.giunei.desafio_ifood.services.aws.AwsSnsService;
import com.giunei.desafio_ifood.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;
    private final AwsSnsService snsService;

    public CategoryService(CategoryRepository repository,
                           AwsSnsService snsService) {
        this.repository = repository;
        this.snsService = snsService;
    }

    public Category insert(CategoryDTO categoryData) {
        Category category = new Category(categoryData);
        this.repository.save(category);
        this.snsService.publish(new MessageDTO(category.toString()));
        return category;
    }

    public Category update(String id, CategoryDTO categoryData){
        Category category = this.repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if(!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if(!categoryData.description().isEmpty()) category.setDescription(categoryData.description());

        this.repository.save(category);
        this.snsService.publish(new MessageDTO(category.toString()));

        return category;
    }

    public void delete(String id) {
        Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        this.repository.delete(category);
        this.snsService.publish(new MessageDTO(category.deleteToString()));
    }

    public List<Category> getAll() {
        return this.repository.findAll();
    }

    public Optional<Category> getById(String id) {
        return this.repository.findById(id);
    }
}
