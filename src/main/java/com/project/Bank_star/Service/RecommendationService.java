package com.project.Bank_star.Service;

import com.project.Bank_star.Model.UserFinancial;
import com.project.Bank_star.Recommendation.Recommendation001;
import com.project.Bank_star.Recommendation.RecommendationResponse;
import com.project.Bank_star.Repository.RecommendationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RecommendationService {
    private static final Logger log = LoggerFactory.getLogger(RecommendationService.class);
    private final RecommendationRepository repository;
    private final List<RecommendationRuleSet> ruleSets;

    public RecommendationService(RecommendationRepository repository, List<RecommendationRuleSet> ruleSets) {
        this.repository = repository;
        this.ruleSets = ruleSets;
    }

    public RecommendationResponse getRecommendations(UUID userId, UserFinancial metrics) {
        log.info("Getting recommendations for user: {}", userId);

        List<Recommendation001> recommendations = new ArrayList<>();

        for (RecommendationRuleSet ruleSet : ruleSets) {
            ruleSet.applyRuleSet(userId, metrics)
                    .ifPresent(recommendations::add);
        }

        log.info("Found {} recommendations for user: {}", recommendations.size(), userId);
        return new RecommendationResponse(userId, recommendations);
    }
}