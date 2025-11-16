package com.project.Bank_star.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class DynamicRuleRequest {
    private String productName;
    private UUID productId;
    private String productText;
    private List<RuleQuery> rule;

}