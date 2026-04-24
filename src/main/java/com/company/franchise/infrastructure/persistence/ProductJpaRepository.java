package com.company.franchise.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    @Query("""
            SELECT p FROM ProductEntity p
            WHERE p.branch.franchise.id = :franchiseId
            AND p.stock = (
                SELECT MAX(p2.stock)
                FROM ProductEntity p2
                WHERE p2.branch.id = p.branch.id
            )
        """)
    List<ProductEntity> findTopProductsByFranchise(@Param("franchiseId") Long franchiseId);
}
