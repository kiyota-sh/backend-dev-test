package com.company.franchise.domain.ports.out;

import com.company.franchise.domain.model.Branch;

public interface BranchRepositoryPort {
    Branch save(Long franchiseId, Branch branch);
    Branch updateName(Long id, String name);
}
