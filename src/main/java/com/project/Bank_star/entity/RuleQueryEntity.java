package com.project.Bank_star.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "rule_queries")
public class RuleQueryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "query_type", nullable = false)
    private QueryType query;

    @SuppressWarnings("deprecation")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "rule_query_arguments", joinColumns = @JoinColumn(name = "rule_query_id"))
    @Column(name = "argument")
    private List<String> arguments = new ArrayList<>();

    @Column(name = "negate")
    private Boolean negate;

    @Column(name = "dynamic_rule_id", insertable = false, updatable = false)
    private UUID dynamicRuleId;
 }


