package com.company.franchise.infrastructure.web.controller;

import com.company.franchise.application.useCase.UpdateBranchNameService;
import com.company.franchise.domain.model.Branch;
import com.company.franchise.domain.ports.out.BranchRepositoryPort;
import com.company.franchise.infrastructure.web.dto.CreateBranchRequest;
import com.company.franchise.infrastructure.web.dto.UpdateFranchiseNameRequest;
import com.company.franchise.infrastructure.web.dto.UpdateNameRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branches")
@Tag(name = "Branches")
public class BranchController {
    private final BranchRepositoryPort branchRepository;
    private final UpdateBranchNameService updateBranchNameService;

    public BranchController(BranchRepositoryPort branchRepository, UpdateBranchNameService updateBranchNameService) {
        this.branchRepository = branchRepository;
        this.updateBranchNameService = updateBranchNameService;
    }

    @Operation(summary = "Create a branch for a franchise")
    @PostMapping
    public Branch create(@RequestBody CreateBranchRequest req) {
        return branchRepository.save(
                req.getFranchiseId(),
                new Branch(null, req.getName(), null, null)
        );
    }

    @Operation(summary = "Update branch name")
    @PutMapping("/{id}/name")
    public Branch updateName(
        @Parameter(description = "Branch ID") @PathVariable Long id,
        @RequestBody UpdateNameRequest req
        ) {
        return updateBranchNameService.execute(id, req.getName());
    }
}