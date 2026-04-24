package com.company.franchise.application.useCase;

import com.company.franchise.domain.model.Product;
import com.company.franchise.domain.ports.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateStockService {

    private final ProductRepositoryPort repository;

    public UpdateStockService(ProductRepositoryPort repository) {
        this.repository = repository;
    }

    public Product execute(Long id, int stock) {
        return repository.updateStock(id, stock);
    }
}