package com.project.Bank_star.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "rule_queries")
public class RuleQueryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "query_type", nullable = false)
    private QueryType query;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "rule_query_arguments", joinColumns = @JoinColumn(name = "rule_query_id"))
    @Column(name = "argument")
    private List<String> arguments = new ArrayList<>();

    @Column(name = "negate")
    private Boolean negate;

    @Column(name = "dynamic_rule_id", insertable = false, updatable = false)
    private UUID dynamicRuleId;

    public RuleQueryEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QueryType getQuery() {
        return query;
    }

    public void setQuery(QueryType query) {
        this.query = query;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public Boolean getNegate() {
        return negate;
    }

    public void setNegate(Boolean negate) {
        this.negate = negate;
    }

    public UUID getDynamicRuleId() {
        return dynamicRuleId;
    }

    public void setDynamicRuleId(UUID dynamicRuleId) {
        this.dynamicRuleId = dynamicRuleId;
    }
}