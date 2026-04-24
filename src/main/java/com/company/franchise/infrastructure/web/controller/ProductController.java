package com.company.franchise.infrastructure.web.controller;

import com.company.franchise.application.useCase.UpdateProductNameService;
import com.company.franchise.domain.model.Branch;
import com.company.franchise.domain.model.Product;
import com.company.franchise.domain.ports.out.ProductRepositoryPort;
import com.company.franchise.infrastructure.web.dto.CreateProductRequest;
import com.company.franchise.infrastructure.web.dto.UpdateNameRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Products")
public class ProductController {
    private final ProductRepositoryPort repository;
    private final UpdateProductNameService updateProductNameService;

    public ProductController(ProductRepositoryPort repository, UpdateProductNameService updateProductNameService) {
        this.repository = repository;
        this.updateProductNameService = updateProductNameService;
    }

    @Operation(summary = "Create product in branch")
    @PostMapping
    public Product create(@RequestBody CreateProductRequest req) {
        return repository.save(req.getBranchId(), new Product(null, req.getName(), req.getStock(), null));
    }

    @Operation(summary = "Delete product")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.delete(id);
    }

    @Operation(summary = "Update product stock")
    @PutMapping("/{id}/stock")
    public Product updateStock(
        @PathVariable Long id,
        @Parameter(description = "New stock value") @RequestParam int stock) {
        return repository.updateStock(id, stock);
    }

    @Operation(summary = "Get top stock products by franchise")
    @GetMapping("/top-stock/{franchiseId}")
    public List<Product> topProducts(@PathVariable Long franchiseId) {
        return repository.findTopProductsByFranchise(franchiseId);
    }

    @Operation(summary = "Update product name")
    @PutMapping("/{id}/name")
    public Product updateName(
        @Parameter(description = "Product ID") @PathVariable Long id,
        @RequestBody UpdateNameRequest req
    ) {
        return updateProductNameService.execute(id, req.getName());
    }
}