package com.project.Bank_star.recommendation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.project.Bank_star.entity.QueryType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleCondition {
    private QueryType query;
    private List<String> arguments;
    private boolean negate;
}


