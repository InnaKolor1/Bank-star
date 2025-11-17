package com.project.Bank_star.service;

import com.project.Bank_star.entity.DynamicRuleEntity;
import com.project.Bank_star.entity.RuleQueryEntity;
import com.project.Bank_star.model.UserFinancial;
import com.project.Bank_star.recommendation.Recommendation001;
import com.project.Bank_star.recommendation.RecommendationResponse;
import com.project.Bank_star.repository.RecommendationRepository;
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
        UserFinancial metrics = fetchUserFinancialData(userId);
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

    private UserFinancial fetchUserFinancialData(UUID userId) {
        return new UserFinancial(1L, 0L, 1500.0, 0L, 5000.0);
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
