package com.company.franchise.infrastructure.persistence.mapper;

import com.company.franchise.domain.model.Product;
import com.company.franchise.infrastructure.persistence.ProductEntity;

public class ProductMapper {
    public static ProductEntity toEntity(Product p) {
        ProductEntity e = new ProductEntity();
        e.setName(p.getName());
        e.setStock(p.getStock());
        return e;
    }

    public static Product toDomain(ProductEntity e) {
        return new Product(e.getId(), e.getName(), e.getStock(), e.getBranch().getId());
    }
}