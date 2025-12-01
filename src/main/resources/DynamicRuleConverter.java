package com.project.Bank_star.service;

import com.project.Bank_star.entity.DynamicRuleEntity;
import com.project.Bank_star.entity.RuleQueryEntity;
import com.project.Bank_star.Model.DynamicRuleRequest;
import com.project.Bank_star.Model.RuleQuery;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DynamicRuleConverter {

    public DynamicRuleEntity toEntity(DynamicRuleRequest request) {
        DynamicRuleEntity entity = new DynamicRuleEntity();
        entity.setProductName(request.getProductName());
        entity.setProductId(request.getProductId());
        entity.setProductText(request.getProductText());

        if (request.getRule() != null) {
            entity.setRule(request.getRule().stream().map(this::toRuleQueryEntity).collect(Collectors.toList()));
        }

        return entity;
    }


    private RuleQueryEntity toRuleQueryEntity(RuleQuery model) {
        RuleQueryEntity entity = new RuleQueryEntity();
        entity.setQuery(model.getQuery());
        entity.setArguments(model.getArguments());
        entity.setNegate(model.getNegate());
        return entity;
    }

    private RuleQuery toRuleQueryModel(RuleQueryEntity entity) {
        RuleQuery model = new RuleQuery();
        model.setQuery(entity.getQuery());
        model.setArguments(entity.getArguments());
        model.setNegate(entity.getNegate());
        return model;
    }
}