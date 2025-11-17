package ProjectAndRules;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Bank_star.entity.QueryType;
import com.project.Bank_star.model.UserFinancial;
import com.project.Bank_star.repository.RecommendationRepository;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.project.Bank_star.dinamic.RuleCondition;
import com.project.Bank_star.dinamic.DynamicRule;

import java.util.List;
import java.util.UUID;

@Component
public class DynamicRuleEngine {

    private static final Logger log = LoggerFactory.getLogger(DynamicRuleEngine.class);
    @Getter
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RecommendationRepository recommendationRepository;

    public DynamicRuleEngine(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    public boolean evaluateRule(DynamicRule rule, UUID userId, UserFinancial metrics) {
        try {
            @SuppressWarnings("deprecation") List<RuleCondition> conditions = objectMapper.readValue(
                    rule.getRuleJson(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, RuleCondition.class)
            );

            boolean result = true;
            for (RuleCondition condition : conditions) {
                boolean conditionResult = evaluateCondition(condition, userId, metrics);
                if (condition.isNegate()) {
                    conditionResult = !conditionResult;
                }
                result = conditionResult;

                if (!result) break;
            }

            return result;

        } catch (JsonProcessingException e) {
            log.error("Error parsing rule conditions for rule {}: {}", rule.getId(), e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("Error evaluating rule {}: {}", rule.getId(), e.getMessage());
            return false;
        }
    }

    private boolean evaluateCondition(RuleCondition condition, UUID userId, UserFinancial metrics) {
        QueryType queryType = condition.getQuery();
        @SuppressWarnings("deprecation") List<String> arguments = condition.getArguments();

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
