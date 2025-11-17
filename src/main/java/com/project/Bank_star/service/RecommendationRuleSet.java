package com.project.Bank_star.service;

import com.project.Bank_star.model.UserFinancial;
import com.project.Bank_star.recommendation.Recommendation001;

import java.util.Optional;
import java.util.UUID;

public interface RecommendationRuleSet {

    Optional<Recommendation001> applyRuleSet(UUID userId, UserFinancial metrics);
}

