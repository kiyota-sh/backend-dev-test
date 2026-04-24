package com.company.franchise.infrastructure.persistence.mapper;

import com.company.franchise.domain.model.Branch;
import com.company.franchise.infrastructure.persistence.BranchEntity;

import java.util.List;

public class BranchMapper {

    public static BranchEntity toEntity(Branch b) {
        BranchEntity e = new BranchEntity();
        e.setName(b.getName());
        return e;
    }

    public static Branch toDomain(BranchEntity e) {
        return new Branch(e.getId(), e.getName(), e.getFranchise().getId(), List.of());
    }
}