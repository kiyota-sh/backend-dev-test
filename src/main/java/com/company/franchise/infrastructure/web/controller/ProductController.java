package com.company.franchise.infrastructure.web.controller;

import com.company.franchise.domain.model.Product;
import com.company.franchise.domain.ports.out.ProductRepositoryPort;
import com.company.franchise.infrastructure.web.dto.CreateProductRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepositoryPort repository;

    public ProductController(ProductRepositoryPort repository) {
        this.repository = repository;
    }

    @PostMapping
    public Product create(@RequestBody CreateProductRequest req) {
        return repository.save(req.getBranchId(), new Product(null, req.getName(), req.getStock(), null));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.delete(id);
    }

    @PutMapping("/{id}/stock")
    public Product updateStock(@PathVariable Long id, @RequestParam int stock) {
        return repository.updateStock(id, stock);
    }
}