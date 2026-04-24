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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FranchiseControllerTest extends BaseIntegrationTest {
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
    void shouldCreateFranchise() throws Exception {
        mockMvc.perform(post("/franchises")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Nike\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Nike"));
    }

    @Test
    void shouldUpdateFranchiseName() throws Exception {
        Long fId = createFranchise("Nike");

        mockMvc.perform(put("/franchises/%d/name".formatted(fId))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Cocheros\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Cocheros"));
    }

    @Test
    void shouldReturn404IfFranchiseNotExists() throws Exception {
        mockMvc.perform(put("/franchises/999/name")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"X\"}"))
            .andExpect(status().isNotFound());
    }
}
