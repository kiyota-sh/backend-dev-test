package com.company.franchise.infrastructure.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to update franchise name")
public class UpdateFranchiseNameRequest {
    @Schema(example = "Cocheros")
    private String name;

    public String getName() {
        return name;
    }
}