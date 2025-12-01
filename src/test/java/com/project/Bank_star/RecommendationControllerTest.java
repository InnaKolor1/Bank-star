package com.project.Bank_star;

import com.project.Bank_star.controller.RecommendationController;
import com.project.Bank_star.Model.UserFinancial;
import com.project.Bank_star.recommendation.Recommendation001;
import com.project.Bank_star.recommendation.RecommendationResponse;
import com.project.Bank_star.service.RecommendationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecommendationController.class)
class RecommendationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecommendationService recommendationService;

    @Test
    void getRecommendations_ShouldReturnRecommendations() throws Exception {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserFinancial userFinancial = new UserFinancial(1L, 0L, 1000.0);

        Recommendation001 recommendation = new Recommendation001(
                UUID.fromString("147f6a0f-3b91-413b-ab99-87f081d60d5a"),
                "Invest 500",
                "Test recommendation text"
        );

        RecommendationResponse response = new RecommendationResponse(userId, List.of(recommendation));

        when(recommendationService.getRecommendations(any(UUID.class)))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/recommendation/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "debitProducts": 1,
                        "investProducts": 0,
                        "sumSavingDeposits": 1000.0
                    }
                    """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId.toString()))
                .andExpect(jsonPath("$.recommendations[0].name").value("Invest 500"));
    }

    @Test
    void healthCheck_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/recommendation/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Recommendation service is running"));
    }
}
