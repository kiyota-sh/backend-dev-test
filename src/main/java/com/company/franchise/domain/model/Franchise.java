package com.company.franchise.domain.model;

import java.util.List;

public class Franchise {
    private Long id;
    private String name;
    private List<Branch> branches;

    public Franchise(Long id, String name, List<Branch> branches) {
        this.id = id;
        this.name = name;
        this.branches = branches;
    }

    public String getName() { return name; }
    public Long getId() { return id; }
    public List<Branch> getBranches() { return branches; }
}
