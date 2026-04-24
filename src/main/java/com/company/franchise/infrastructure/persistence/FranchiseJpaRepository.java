package com.company.franchise.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseJpaRepository extends JpaRepository<FranchiseEntity, Long> {}
