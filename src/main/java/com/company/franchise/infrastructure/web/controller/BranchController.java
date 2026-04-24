package com.company.franchise.infrastructure.web.controller;

import com.company.franchise.domain.model.Branch;
import com.company.franchise.domain.ports.out.BranchRepositoryPort;
import com.company.franchise.infrastructure.web.dto.CreateBranchRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branches")
@Tag(name = "Branches")
public class BranchController {

    private final BranchRepositoryPort branchRepository;

    public BranchController(BranchRepositoryPort branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Operation(summary = "Create a branch for a franchise")
    @PostMapping
    public Branch create(@RequestBody CreateBranchRequest req) {
        return branchRepository.save(
                req.getFranchiseId(),
                new Branch(null, req.getName(), null, null)
        );
    }
}