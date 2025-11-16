package com.project.Bank_star.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
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

    @SuppressWarnings("deprecation")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "dynamic_rule_id")
    private List<RuleQueryEntity> rule = new ArrayList<>();

    public DynamicRuleEntity() {
    }

    public byte[] getRuleJson() {
        return new byte[0];
    }
}