package com.company.franchise.domain.ports.out;

import com.company.franchise.domain.model.Franchise;

public interface FranchiseRepositoryPort {
    Franchise save(Franchise franchise);
    Franchise updateName(Long id, String name);
    Franchise findById(Long id);
}
