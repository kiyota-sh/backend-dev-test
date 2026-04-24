package com.company.franchise.infrastructure.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to update name attribute")
public class UpdateNameRequest {
    @Schema(example = "Name")
    private String name;

    public String getName() {
        return name;
    }
}
