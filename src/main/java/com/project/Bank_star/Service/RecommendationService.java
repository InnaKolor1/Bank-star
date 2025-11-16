package com.project.Bank_star.Service;

import com.project.Bank_star.Entity.DynamicRuleEntity;
import com.project.Bank_star.Entity.RuleQueryEntity;
import com.project.Bank_star.Model.UserFinancial;
import com.project.Bank_star.Recommendation.Recommendation001;
import com.project.Bank_star.Recommendation.RecommendationResponse;
import com.project.Bank_star.Repository.RecommendationRepository;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RecommendationService {

    private final RecommendationRepository repository;
    private final List<RecommendationRuleSet> ruleSets;
    private final DynamicRuleService dynamicRuleService;
    private final RuleEngineService ruleEngineService;

    public RecommendationService(RecommendationRepository repository,
                                 List<RecommendationRuleSet> ruleSets,
                                 DynamicRuleService dynamicRuleService,
                                 RuleEngineService ruleEngineService) {
        this.repository = repository;
        this.ruleSets = ruleSets;
        this.dynamicRuleService = dynamicRuleService;
        this.ruleEngineService = ruleEngineService;
    }

    public RecommendationResponse getRecommendations(UUID userId) {
        // Create UserFinancial metrics for the user
        // In a real application, this would fetch actual user data from the database
        UserFinancial metrics = new UserFinancial(0L, 0L, 0.0, 0L, 0.0);

        List<Recommendation001> recommendations = new ArrayList<>();

        for (RecommendationRuleSet ruleSet : ruleSets) {
            ruleSet.applyRuleSet(userId, metrics).ifPresent(recommendations::add);
        }

        List<DynamicRuleEntity> dynamicRules = dynamicRuleService.getAllActiveRules();
        for (DynamicRuleEntity dynamicRule : dynamicRules) {
            if (evaluateDynamicRule(userId, dynamicRule)) {
                Recommendation001 rec = new Recommendation001(
                        dynamicRule.getProductId(),
                        dynamicRule.getProductName(),
                        dynamicRule.getProductText()
                );
                recommendations.add(rec);
            }
        }

        return new RecommendationResponse(userId, recommendations);
    }

    private boolean evaluateDynamicRule(UUID userId, DynamicRuleEntity dynamicRule) {
        try {
            for (RuleQueryEntity ruleQuery : dynamicRule.getRule()) {
                if (!ruleEngineService.evaluateRule(userId, ruleQuery)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}