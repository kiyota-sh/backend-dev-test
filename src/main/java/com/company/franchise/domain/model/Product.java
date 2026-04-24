package com.company.franchise.domain.model;

public class Product {
    private Long id;
    private String name;
    private int stock;
    private Long branchId;

    public Product(Long id, String name, int stock, Long branchId) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.branchId = branchId;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getStock() { return stock; }
    public Long getBranchId() { return branchId; }
}
