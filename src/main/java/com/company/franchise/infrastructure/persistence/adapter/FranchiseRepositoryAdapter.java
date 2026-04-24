package com.company.franchise.infrastructure.persistence.adapter;

import com.company.franchise.domain.model.Franchise;
import com.company.franchise.domain.ports.out.FranchiseRepositoryPort;
import com.company.franchise.infrastructure.persistence.FranchiseEntity;
import com.company.franchise.infrastructure.persistence.FranchiseJpaRepository;
import com.company.franchise.infrastructure.persistence.mapper.FranchiseMapper;
import org.springframework.stereotype.Repository;

@Repository
public class FranchiseRepositoryAdapter implements FranchiseRepositoryPort {
    private final FranchiseJpaRepository repo;

    public FranchiseRepositoryAdapter(FranchiseJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Franchise save(Franchise franchise) {
        FranchiseEntity entity = FranchiseMapper.toEntity(franchise);
        return FranchiseMapper.toDomain(repo.save(entity));
    }

    @Override
    public Franchise updateName(Long id, String name) {

        FranchiseEntity entity = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Franchise not found"));

        entity.setName(name);

        return FranchiseMapper.toDomain(repo.save(entity));
    }

    @Override
    public Franchise findById(Long id) {
        FranchiseEntity entity = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Franchise not found"));

        return FranchiseMapper.toDomain(entity);
    }
}