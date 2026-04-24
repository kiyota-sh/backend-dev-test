package com.company.franchise.infrastructure.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to create a product")
public class CreateProductRequest {
    @Schema(example = "Burger")
    private String name;

    @Schema(example = "10")
    private int stock;

    @Schema(example = "1")
    private Long branchId;

    public String getName() { return name; }
    public int getStock() { return stock; }
    public Long getBranchId() { return branchId; }
}