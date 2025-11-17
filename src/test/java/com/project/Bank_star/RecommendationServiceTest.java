package com.project.Bank_star;

import com.project.Bank_star.Model.UserFinancial;
import com.project.Bank_star.Recommendation.Recommendation001;
import com.project.Bank_star.Recommendation.RecommendationResponse;
import com.project.Bank_star.Repository.RecommendationRepository;
import com.project.Bank_star.Service.RecommendationRuleSet;
import com.project.Bank_star.Service.RecommendationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecommendationServiceTest {

    @Mock
    private RecommendationRepository repository;

    @Mock
    private RecommendationRuleSet ruleSet1;

    @Mock
    private RecommendationRuleSet ruleSet2;

    @InjectMocks
    private RecommendationService recommendationService;

    @Test
    void getRecommendations_WhenRulesMatch_ShouldReturnRecommendations() {

        UUID userId = UUID.randomUUID();
        UserFinancial metrics = new UserFinancial(1L, 0L, 1000.0);

        Recommendation001 recommendation = new Recommendation001(
                UUID.randomUUID(), "Test Product", "Test Text"
        );

        when(ruleSet1.applyRuleSet(userId, metrics)).thenReturn(Optional.of(recommendation));
        when(ruleSet2.applyRuleSet(userId, metrics)).thenReturn(Optional.empty());

        RecommendationService service = new RecommendationService(repository, List.of(ruleSet1, ruleSet2));


        RecommendationResponse response = service.getRecommendations(userId, metrics);


        assertNotNull(response);
        assertEquals(userId, response.getUserId());
        assertEquals(1, response.getRecommendations().size());
        assertEquals("Test Product", response.getRecommendations().get(0).getName());
    }

    @Test
    void getRecommendations_WhenNoRulesMatch_ShouldReturnEmptyList() {

        UUID userId = UUID.randomUUID();
        UserFinancial metrics = new UserFinancial(0L, 0L, 0.0);

        when(ruleSet1.applyRuleSet(userId, metrics)).thenReturn(Optional.empty());
        when(ruleSet2.applyRuleSet(userId, metrics)).thenReturn(Optional.empty());

        RecommendationService service = new RecommendationService(repository, List.of(ruleSet1, ruleSet2));


        RecommendationResponse response = service.getRecommendations(userId, metrics);


        assertNotNull(response);
        assertEquals(userId, response.getUserId());
        assertTrue(response.getRecommendations().isEmpty());
    }
}