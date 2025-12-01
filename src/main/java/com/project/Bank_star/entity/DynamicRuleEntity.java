package com.project.Bank_star.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dynamic_rules")
public class DynamicRuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "product_text", nullable = false, length = 2000)
    private String productText;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "dynamic_rule_id")
    private List<RuleQueryEntity> rule = new ArrayList<>();

    public DynamicRuleEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }



    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }



    public void setProductText(String productText) {
        this.productText = productText;
    }

    public List<RuleQueryEntity> getRule() {
        return rule;
    }

    public void setRule(List<RuleQueryEntity> rule) {
        this.rule = rule;
    }

    public byte[] getRuleJson() {
        return new byte[0];
    }

    public String getProductName() {
        return "";
    }

    public UUID getProductId() {
        return null;
    }

    public String getProductText() {
        return "";
    }
}