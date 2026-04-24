package com.company.franchise;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public abstract class BaseIntegrationTest {
    @Autowired
    protected MockMvc mockMvc;

    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected Long createFranchise(String name) throws Exception {
        String res = mockMvc.perform(post("/franchises")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"" + name + "\"}"))
            .andReturn().getResponse().getContentAsString();

        JsonNode json = objectMapper.readTree(res);
        return json.get("id").asLong();
    }

    protected Long createBranch(String name, Long franchiseId) throws Exception {
        String res = mockMvc.perform(post("/branches")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "name":"%s",
                          "franchiseId": %d
                        }
                    """.formatted(name, franchiseId)))
            .andReturn().getResponse().getContentAsString();

        return objectMapper.readTree(res).get("id").asLong();
    }

    protected Long createProduct(String name, int stock, Long branchId) throws Exception {
        String res = mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "name":"%s",
                          "stock":%d,
                          "branchId": %d
                        }
                    """.formatted(name, stock, branchId)))
            .andReturn().getResponse().getContentAsString();

        return objectMapper.readTree(res).get("id").asLong();
    }
}