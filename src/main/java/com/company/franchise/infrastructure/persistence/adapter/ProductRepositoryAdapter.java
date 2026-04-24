package com.company.franchise.infrastructure.persistence.adapter;

import com.company.franchise.domain.exception.NotFoundException;
import com.company.franchise.domain.model.Product;
import com.company.franchise.domain.ports.out.ProductRepositoryPort;
import com.company.franchise.infrastructure.persistence.BranchEntity;
import com.company.franchise.infrastructure.persistence.BranchJpaRepository;
import com.company.franchise.infrastructure.persistence.ProductEntity;
import com.company.franchise.infrastructure.persistence.ProductJpaRepository;
import com.company.franchise.infrastructure.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryAdapter implements ProductRepositoryPort {
    private final ProductJpaRepository productRepo;
    private final BranchJpaRepository branchRepo;

    public ProductRepositoryAdapter(ProductJpaRepository productRepo,
                                    BranchJpaRepository branchRepo) {
        this.productRepo = productRepo;
        this.branchRepo = branchRepo;
    }

    @Override
    public Product save(Long branchId, Product product) {
        BranchEntity branch = branchRepo.findById(branchId)
            .orElseThrow(() -> new NotFoundException(("Product no found")));

        ProductEntity entity = new ProductEntity();
        entity.setName(product.getName());
        entity.setStock(product.getStock());
        entity.setBranch(branch);

        ProductEntity saved = productRepo.save(entity);

        return new Product(saved.getId(), saved.getName(), saved.getStock(), branchId);
    }

    @Override
    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product updateStock(Long id, int stock) {
        ProductEntity entity = productRepo.findById(id)
            .orElseThrow(() -> new NotFoundException("Product not found"));

        entity.setStock(stock);
        ProductEntity saved = productRepo.save(entity);

        return ProductMapper.toDomain(saved);
    }

    @Override
    public List<Product> findTopProductsByFranchise(Long franchiseId) {
        List<ProductEntity> entities = productRepo.findTopProductsByFranchise(franchiseId);

        return entities.stream()
            .map(ProductMapper::toDomain).toList();
    }
}
