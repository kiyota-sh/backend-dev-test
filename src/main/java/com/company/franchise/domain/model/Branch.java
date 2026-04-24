package com.company.franchise.domain.model;

import java.util.List;

public class Branch {
    private Long id;
    private String name;
    private Long franchiseId;
    private List<Product> products;

    public Branch(Long id, String name, Long franchiseId, List<Product> products) {
        this.id = id;
        this.name = name;
        this.franchiseId = franchiseId;
        this.products = products;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Long getFranchiseId() { return franchiseId; }
    public List<Product> getProducts() { return products; }
}
