package com.company.franchise.domain.ports.out;

import com.company.franchise.domain.model.Product;

import java.util.List;

public interface ProductRepositoryPort {
    Product save(Long branchId, Product product);
    void delete(Long id);
    Product updateStock(Long id, int stock);
    List<Product> findTopProductsByFranchise(Long franchiseId);
    Product updateName(Long id, String name);
}