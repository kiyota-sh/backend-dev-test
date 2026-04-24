package com.company.franchise.application.useCase;

import com.company.franchise.domain.model.Branch;
import com.company.franchise.domain.ports.out.BranchRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateBranchNameService {
    private final BranchRepositoryPort repository;

    public UpdateBranchNameService(BranchRepositoryPort repository) {
        this.repository = repository;
    }

    public Branch execute(Long id, String name) {
        return repository.updateName(id, name);
    }
}