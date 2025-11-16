package com.project.Bank_star.Service;

import com.project.Bank_star.Entity.QueryType;
import com.project.Bank_star.Entity.RuleQueryEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RuleEngineService {
    private final UserKnowledgeRepository userKnowledgeRepository;

    public RuleEngineService(UserKnowledgeRepository userKnowledgeRepository) {
        this.userKnowledgeRepository = userKnowledgeRepository;
    }

    public boolean evaluateRule(UUID userId, RuleQueryEntity ruleQuery) {
        boolean result = evaluateQuery(userId, ruleQuery);
        return (ruleQuery.getNegate() != null && ruleQuery.getNegate()) != result;
    }

    private boolean evaluateQuery(UUID userId, RuleQueryEntity ruleQuery) {
        QueryType queryType = ruleQuery.getQuery();
        List<String> arguments = ruleQuery.getArguments();

        return switch (queryType) {
            case USER_OF -> userKnowledgeRepository.isUserOfProduct(userId, arguments.get(0));
            case ACTIVE_USER_OF -> userKnowledgeRepository.isActiveUserOfProduct(userId, arguments.get(0));
            case TRANSACTION_SUM_COMPARE -> userKnowledgeRepository.compareTransactionSumWithConstant(
                    userId, arguments.get(0), arguments.get(1), arguments.get(2), Double.parseDouble(arguments.get(3)));
            case TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW ->
                    userKnowledgeRepository.compareDepositWithWithdraw(userId, arguments.get(0), arguments.get(1));
            default -> throw new IllegalArgumentException("Unknown query type: " + queryType);
        };
    }
}
