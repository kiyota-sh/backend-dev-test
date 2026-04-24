package com.company.franchise.domain.repository;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "branches")
public class BranchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    private List<ProductEntity> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id")
    private FranchiseEntity franchise;
}
