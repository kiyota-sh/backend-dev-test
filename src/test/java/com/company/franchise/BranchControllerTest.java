package com.company.franchise;

import com.company.franchise.infrastructure.persistence.BranchJpaRepository;
import com.company.franchise.infrastructure.persistence.FranchiseJpaRepository;
import com.company.franchise.infrastructure.persistence.ProductJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BranchControllerTest extends BaseIntegrationTest {
    @Autowired
    private ProductJpaRepository productRepo;
    @Autowired
    private BranchJpaRepository branchRepo;
    @Autowired
    private FranchiseJpaRepository franchiseRepo;

    @BeforeEach
    void cleanDatabase() {
        productRepo.deleteAll();
        branchRepo.deleteAll();
        franchiseRepo.deleteAll();
    }

    @Test
    void shouldCreateBranch() throws Exception {
        Long fId = createFranchise("Nike");

        mockMvc.perform(post("/branches")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "name": "Main Store",
                          "franchiseId": %d
                        }
                    """.formatted(fId)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Main Store"));
    }

    @Test
    void shouldFailIfFranchiseNotExists() throws Exception {
        mockMvc.perform(post("/branches")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "name": "Main",
                          "franchiseId": 999
                        }
                    """))
            .andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateBranchName() throws Exception {
        Long fId = createFranchise("Nike");
        Long bId = createBranch("Main", fId);

        mockMvc.perform(put("/branches/%d/name".formatted(bId))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Central\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Central"));
    }
}
