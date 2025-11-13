package ProjectAndRules;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import com.project.Bank_star.Model.DynamicRuleRequest;
import com.project.Bank_star.Model.DynamicRuleResponse;
import com.project.Bank_star.dinamic.RuleCondition;
import com.project.Bank_star.dinamic.DynamicRule;

import java.util.List;
import java.util.UUID;

@Component
public class DynamicRuleTelegram {
    private final ObjectMapper objectMapper = new ObjectMapper();


    public DynamicRule toEntity(DynamicRuleRequest request) {
        DynamicRule rule = new DynamicRule();
        rule.setId(UUID.randomUUID());
        rule.setProductName(request.getProductName());
        rule.setProductId(request.getProductId());
        rule.setProductText(request.getProductText());

        try {
            rule.setRuleJson(objectMapper.writeValueAsString(request.getRule()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing rule to JSON", e);
        }

        return rule;
    }

    public DynamicRuleResponse toResponse(DynamicRule entity) {
        try {
            List<RuleCondition> rule = objectMapper.readValue(
                    entity.getRuleJson(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, RuleCondition.class)
            );

            return new DynamicRuleResponse(
                    entity.getId(),
                    entity.getProductName(),
                    entity.getProductId(),
                    entity.getProductText(),
                    rule
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error deserializing rule from JSON", e);
        }
    }
}
