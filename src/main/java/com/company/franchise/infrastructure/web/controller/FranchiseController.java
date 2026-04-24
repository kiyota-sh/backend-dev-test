package com.company.franchise.infrastructure.web.controller;

import com.company.franchise.application.useCase.CreateFranchiseService;
import com.company.franchise.domain.model.Franchise;
import com.company.franchise.domain.ports.in.UpdateFranchiseNameUseCase;
import com.company.franchise.infrastructure.web.dto.CreateFranchiseRequest;
import com.company.franchise.infrastructure.web.dto.UpdateFranchiseNameRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/franchises")
@Tag(name = "Franchises")
public class FranchiseController {
    private final CreateFranchiseService createService;
    private final UpdateFranchiseNameUseCase updateService;

    public FranchiseController(CreateFranchiseService createService, UpdateFranchiseNameUseCase updateService) {
        this.createService = createService;
        this.updateService = updateService;
    }

    @Operation(summary = "Create a new franchise")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Franchise created"),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public Franchise create(@RequestBody CreateFranchiseRequest req) {
        return createService.execute(req.getName());
    }

    @Operation(summary = "Update franchise name")
    @PutMapping("/{id}/name")
    public Franchise updateName(
        @Parameter(description = "Franchise ID") @PathVariable Long id,
        @RequestBody UpdateFranchiseNameRequest req
    ) {
        return updateService.execute(id, req.getName());
    }
}
