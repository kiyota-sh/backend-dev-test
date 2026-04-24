package com.company.franchise.domain.ports.in;

import com.company.franchise.domain.model.Franchise;

public interface CreateFranchiseUseCase {
    Franchise execute(String name);
}