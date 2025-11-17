package com.project.Bank_star.model;

import com.project.Bank_star.entity.QueryType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RuleQuery {
    private QueryType query;
    private List<String> arguments;
    private Boolean negate;


    public RuleQuery(QueryType query, List<String> arguments, Boolean negate) {
        this.query = query;
        this.arguments = arguments;
        this.negate = negate;
    }

}

