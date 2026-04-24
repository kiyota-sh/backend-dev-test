package com.company.franchise.infrastructure.web.dto;

public class CreateBranchRequest {
    private String name;
    private Long franchiseId;

    public String getName() { return name; }
    public Long getFranchiseId() { return franchiseId; }
}