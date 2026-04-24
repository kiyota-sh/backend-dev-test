package com.company.franchise.infrastructure.persistence.mapper;

import com.company.franchise.domain.model.Franchise;
import com.company.franchise.infrastructure.persistence.FranchiseEntity;

import java.util.List;

public class FranchiseMapper {
    public static FranchiseEntity toEntity(Franchise f) {
        FranchiseEntity e = new FranchiseEntity();
        e.setName(f.getName());
        return e;
    }

    public static Franchise toDomain(FranchiseEntity e) {
        return new Franchise(e.getId(), e.getName(), List.of());
    }
}