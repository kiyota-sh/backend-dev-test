package com.company.franchise.application.useCase;

import com.company.franchise.domain.model.Franchise;
import com.company.franchise.domain.ports.in.UpdateFranchiseNameUseCase;
import com.company.franchise.domain.ports.out.FranchiseRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateFranchiseNameService implements UpdateFranchiseNameUseCase {
    private final FranchiseRepositoryPort repository;

    public UpdateFranchiseNameService(FranchiseRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Franchise execute(Long id, String newName) {
        Franchise franchise = repository.findById(id);

        Franchise updated = new Franchise(
                franchise.getId(),
                newName,
                franchise.getBranches()
        );

        return repository.updateName(id, newName);
    }
}