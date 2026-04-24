package com.company.franchise.infrastructure.web.controller;

import com.company.franchise.application.useCase.CreateFranchiseService;
import com.company.franchise.domain.model.Franchise;
import com.company.franchise.domain.ports.in.UpdateFranchiseNameUseCase;
import com.company.franchise.infrastructure.web.dto.CreateFranchiseRequest;
import com.company.franchise.infrastructure.web.dto.UpdateFranchiseNameRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/franchises")
public class FranchiseController {
    private final CreateFranchiseService createService;
    private final UpdateFranchiseNameUseCase updateService;

    public FranchiseController(CreateFranchiseService createService, UpdateFranchiseNameUseCase updateService) {
        this.createService = createService;
        this.updateService = updateService;
    }

    @PostMapping
    public Franchise create(@RequestBody CreateFranchiseRequest req) {
        return createService.execute(req.getName());
    }

    @PutMapping("/{id}/name")
    public Franchise updateName(@PathVariable Long id, @RequestBody UpdateFranchiseNameRequest req) {

        return updateService.execute(id, req.getName());
    }
}
