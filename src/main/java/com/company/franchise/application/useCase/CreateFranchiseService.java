package com.company.franchise.application.useCase;

import com.company.franchise.domain.model.Franchise;
import com.company.franchise.domain.ports.in.CreateFranchiseUseCase;
import com.company.franchise.domain.ports.out.FranchiseRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateFranchiseService implements CreateFranchiseUseCase {
    private final FranchiseRepositoryPort repository;

    public CreateFranchiseService(FranchiseRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Franchise execute(String name) {
        return repository.save(new Franchise(null, name, null));
    }
}
