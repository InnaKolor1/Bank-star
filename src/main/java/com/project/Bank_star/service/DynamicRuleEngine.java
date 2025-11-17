package com.project.Bank_star.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Bank_star.recommendation.RuleCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.project.Bank_star.model.UserFinancial;
import com.project.Bank_star.entity.QueryType;
import com.project.Bank_star.repository.RecommendationRepository;

import java.util.List;
import java.util.UUID;

@Component("dynamicRuleEngine")
public class DynamicRuleEngine {

    private static final Logger log = LoggerFactory.getLogger(DynamicRuleEngine.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RecommendationRepository recommendationRepository;

    public DynamicRuleEngine(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @SuppressWarnings("deprecation")
    private boolean evaluateCondition(RuleCondition condition, UUID userId, UserFinancial metrics) {
        QueryType queryType = condition.getQuery();
        List<String> arguments = condition.getArguments();

        return switch (queryType) {
            case USER_OF -> arguments.size() >= 1 &&
                    recommendationRepository.isUserOfProductType(userId, arguments.get(0));
            case ACTIVE_USER_OF -> arguments.size() >= 1 &&
                    recommendationRepository.isActiveUserOfProductType(userId, arguments.get(0));
            case TRANSACTION_SUM_COMPARE -> {
                if (arguments.size() >= 3) {
                    Double actualSum = recommendationRepository.getTransactionSum(
                            userId, arguments.get(0), arguments.get(1));
                    Double expectedSum = Double.parseDouble(arguments.get(2));
                    yield actualSum >= expectedSum;
                }
                yield false;
            }
            case TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW -> {
                if (arguments.size() >= 2) {
                    Double depositSum = recommendationRepository.getTransactionSum(
                            userId, arguments.get(0), "DEPOSIT");
                    Double withdrawSum = recommendationRepository.getTransactionSum(
                            userId, arguments.get(0), "WITHDRAW");
                    Double expectedRatio = Double.parseDouble(arguments.get(1));
                    yield depositSum > withdrawSum * expectedRatio;
                }
                yield false;
            }
            default -> {
                log.warn("Unknown query type: {}", queryType);
                yield false;
            }
        };
    }
}