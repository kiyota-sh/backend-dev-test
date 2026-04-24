package com.company.franchise.domain.ports;

import com.company.franchise.domain.model.Product;

public interface ProductRepositoryPort {
    Product save(Product product);
    void delete(Long id);
    Product updateStock(Long id, int stock);
}
