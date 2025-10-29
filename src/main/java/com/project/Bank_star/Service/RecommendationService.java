package com.project.Bank_star.Service;

import com.project.Bank_star.Entity.RecommendationRule;
import com.project.Bank_star.Recommendation.RecommendationResponse;
import com.project.Bank_star.Repository.RecommendationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

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

}
