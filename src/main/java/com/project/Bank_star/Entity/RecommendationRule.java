package com.project.Bank_star.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
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

}
