package com.company.franchise.infrastructure.persistence;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "franchises")
public class FranchiseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "franchise", fetch = FetchType.LAZY)
    private List<BranchEntity> branches;
}
