package com.company.franchise.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchJpaRepository extends JpaRepository<BranchEntity, Long> {}
