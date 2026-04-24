package com.company.franchise.infrastructure.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to create a branch")
public class CreateBranchRequest {
    @Schema(example = "Main Store")
    private String name;

    @Schema(example = "1")
    private Long franchiseId;

    public String getName() { return name; }
    public Long getFranchiseId() { return franchiseId; }
}