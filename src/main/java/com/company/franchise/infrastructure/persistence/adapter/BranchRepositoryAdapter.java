package com.company.franchise.infrastructure.persistence.adapter;

import com.company.franchise.domain.model.Branch;
import com.company.franchise.domain.ports.out.BranchRepositoryPort;
import com.company.franchise.infrastructure.persistence.BranchEntity;
import com.company.franchise.infrastructure.persistence.BranchJpaRepository;
import com.company.franchise.infrastructure.persistence.FranchiseEntity;
import com.company.franchise.infrastructure.persistence.FranchiseJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BranchRepositoryAdapter implements BranchRepositoryPort {
    private final BranchJpaRepository branchRepo;
    private final FranchiseJpaRepository franchiseRepo;

    public BranchRepositoryAdapter(BranchJpaRepository branchRepo,
                                   FranchiseJpaRepository franchiseRepo) {
        this.branchRepo = branchRepo;
        this.franchiseRepo = franchiseRepo;
    }

    @Override
    public Branch save(Long franchiseId, Branch branch) {

        FranchiseEntity franchise = franchiseRepo.findById(franchiseId)
                .orElseThrow();

        BranchEntity entity = new BranchEntity();
        entity.setName(branch.getName());
        entity.setFranchise(franchise);

        BranchEntity saved = branchRepo.save(entity);

        return new Branch(saved.getId(), saved.getName(), franchiseId, null);
    }
}
