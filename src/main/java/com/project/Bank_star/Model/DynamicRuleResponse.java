package com.project.Bank_star.Model;

import com.project.Bank_star.dinamic.RuleCondition;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor

public class DynamicRuleResponse {
    private UUID id;
    private String productName;
    private UUID productId;
    private String productText;
    private List<RuleCondition> rule;



    public DynamicRuleResponse(UUID id, String productName, UUID productId, String productText, List<RuleCondition> rule) {
        this.id = id;
        this.productName = productName;
        this.productId = productId;
        this.productText = productText;
        this.rule = rule;
    }

}
