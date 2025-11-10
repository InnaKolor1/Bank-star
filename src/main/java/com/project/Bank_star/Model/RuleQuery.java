package com.project.Bank_star.Model;

import com.project.Bank_star.Entity.QueryType;

import java.util.List;

public class RuleQuery {
    private QueryType query;
    private List<String> arguments;
    private Boolean negate;

    public RuleQuery() {
    }

    public RuleQuery(QueryType query, List<String> arguments, Boolean negate) {
        this.query = query;
        this.arguments = arguments;
        this.negate = negate;
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
}