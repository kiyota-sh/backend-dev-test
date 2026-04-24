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
class ProductControllerTest extends BaseIntegrationTest {
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
    void shouldCreateProduct() throws Exception {
        Long fId = createFranchise("Nike");
        Long bId = createBranch("Main", fId);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "name": "Shoes",
                          "stock": 100,
                          "branchId": %d
                        }
                    """.formatted(bId)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Shoes"));
    }

    @Test
    void shouldUpdateStock() throws Exception {
        Long fId = createFranchise("Nike");
        Long bId = createBranch("Main", fId);
        Long pId = createProduct("Shoes", 100, bId);

        mockMvc.perform(put("/products/%d/stock?stock=200".formatted(pId)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.stock").value(200));
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        Long fId = createFranchise("Nike");
        Long bId = createBranch("Main", fId);
        Long pId = createProduct("Shoes", 100, bId);

        mockMvc.perform(delete("/products/%d".formatted(pId)))
            .andExpect(status().isOk());
    }

    @Test
    void shouldFailIfProductNotExists() throws Exception {
        mockMvc.perform(put("/products/999/stock?stock=10"))
            .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnTopProductsByFranchise() throws Exception {
        Long fId = createFranchise("Nike");

        Long b1 = createBranch("Store1", fId);
        Long b2 = createBranch("Store2", fId);

        createProduct("A", 10, b1);
        createProduct("B", 50, b1); // top

        createProduct("C", 5, b2);
        createProduct("D", 80, b2); // top

        mockMvc.perform(get("/products/top-stock/%d".formatted(fId)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].name").value("B"))
            .andExpect(jsonPath("$[1].name").value("D"));
    }

    @Test
    void shouldUpdateProductName() throws Exception {
        Long fId = createFranchise("Nike");
        Long bId = createBranch("Main", fId);
        Long pId = createProduct("Shoes", 100, bId);

        mockMvc.perform(put("/products/%d/name".formatted(pId))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Sneakers\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Sneakers"));
    }
}