package com.company.franchise.application.useCase;

import com.company.franchise.domain.model.Product;
import com.company.franchise.domain.ports.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductNameService {

    private final ProductRepositoryPort repository;

    public UpdateProductNameService(ProductRepositoryPort repository) {
        this.repository = repository;
    }

    public Product execute(Long id, String name) {
        return repository.updateName(id, name);
    }
}
