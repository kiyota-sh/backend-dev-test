package com.company.franchise.infrastructure.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to create franchise")
public class CreateFranchiseRequest {
    @Schema(description = "Franchise name", example = "Nike")
    private String name;

    public String getName() { return name; }
}
