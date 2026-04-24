package com.company.franchise.domain.ports.in;

import com.company.franchise.domain.model.Franchise;

public interface UpdateFranchiseNameUseCase {
    Franchise execute(Long id, String newName);
}