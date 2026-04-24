package com.company.franchise.infrastructure.web.dto;

public class CreateProductRequest {
    private String name;
    private int stock;
    private Long branchId;

    public String getName() { return name; }
    public int getStock() { return stock; }
    public Long getBranchId() { return branchId; }
}