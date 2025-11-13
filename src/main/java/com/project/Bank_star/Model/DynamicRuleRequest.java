package com.project.Bank_star.Model;

import java.util.List;
import java.util.UUID;

public class DynamicRuleRequest {
    private String productName;
    private UUID productId;
    private String productText;
    private List<RuleQuery> rule;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getProductText() {
        return productText;
    }

    public void setProductText(String productText) {
        this.productText = productText;
    }

    public List<RuleQuery> getRule() {
        return rule;
    }

    public void setRule(List<RuleQuery> rule) {
        this.rule = rule;
    }
}