package com.project.Bank_star.Entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "recommendation_rules")
public class RecommendationRule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "rule_name", nullable = false)
    private String ruleName;

    @Column(name = "rule_description")
    private String ruleDescription;

    @Column(name = "is_active")
    private Boolean isActive;

    public RecommendationRule() {}

    public RecommendationRule(UUID id, String ruleName, String ruleDescription, Boolean isActive) {
        this.id = id;
        this.ruleName = ruleName;
        this.ruleDescription = ruleDescription;
        this.isActive = isActive;
    }

    public UUID getId() { return id; }
    public String getRuleName() { return ruleName; }
    public String getRuleDescription() { return ruleDescription; }
    public Boolean getIsActive() { return isActive; }

    public void setId(UUID id) { this.id = id; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }
    public void setRuleDescription(String ruleDescription) { this.ruleDescription = ruleDescription; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}