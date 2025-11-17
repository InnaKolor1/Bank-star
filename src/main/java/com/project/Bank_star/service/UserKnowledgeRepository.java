package com.project.Bank_star.service;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserKnowledgeRepository {
    private final SimpleCashService cacheService;

    public UserKnowledgeRepository(SimpleCashService cacheService) {
        this.cacheService = cacheService;
    }

    public boolean isUserOfProduct(UUID userId, String productType) {
        String cacheKey = "USER_OF:" + userId + ":" + productType;

        Boolean cached = cacheService.get(cacheKey);
        if (cached != null) {
            return cached;
        }

        boolean result = executeUserOfQuery(userId, productType);
        cacheService.put(cacheKey, result, 10);
        return result;
    }

    public boolean isActiveUserOfProduct(UUID userId, String productType) {
        String cacheKey = "ACTIVE_USER_OF:" + userId + ":" + productType;

        Boolean cached = cacheService.get(cacheKey);
        if (cached != null) {
            return cached;
        }

        boolean result = executeActiveUserOfQuery(userId, productType);
        cacheService.put(cacheKey, result, 10);
        return result;
    }

    public double getTransactionSum(UUID userId, String productType, String transactionType) {
        String cacheKey = "TRANSACTION_SUM:" + userId + ":" + productType + ":" + transactionType;

        Double cached = cacheService.get(cacheKey);
        if (cached != null) {
            return cached;
        }

        double result = executeTransactionSumQuery(userId, productType, transactionType);
        cacheService.put(cacheKey, result, 10);
        return result;
    }

    public boolean compareTransactionSumWithConstant(UUID userId, String productType, String transactionType, String operator, double compareValue) {
        String cacheKey = "COMPARE_CONST:" + userId + ":" + productType + ":" + transactionType + ":" + operator + ":" + compareValue;

        Boolean cached = cacheService.get(cacheKey);
        if (cached != null) {
            return cached;
        }

        double sum = getTransactionSum(userId, productType, transactionType);
        boolean result = compareValues(sum, compareValue, operator);
        cacheService.put(cacheKey, result, 10);
        return result;
    }

    public boolean compareDepositWithWithdraw(UUID userId, String productType, String operator) {
        String cacheKey = "COMPARE_DEP_WITH:" + userId + ":" + productType + ":" + operator;

        Boolean cached = cacheService.get(cacheKey);
        if (cached != null) {
            return cached;
        }

        double depositSum = getTransactionSum(userId, productType, "DEPOSIT");
        double withdrawSum = getTransactionSum(userId, productType, "WITHDRAW");
        boolean result = compareValues(depositSum, withdrawSum, operator);
        cacheService.put(cacheKey, result, 10);
        return result;
    }

    private boolean compareValues(double value1, double value2, String operator) {
        switch (operator) {
            case ">": return value1 > value2;
            case "<": return value1 < value2;
            case "=": return Math.abs(value1 - value2) < 0.001;
            case ">=": return value1 >= value2;
            case "<=": return value1 <= value2;
            default: throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    private boolean executeUserOfQuery(UUID userId, String productType) {
        return Math.random() > 0.5;
    }

    private boolean executeActiveUserOfQuery(UUID userId, String productType) {
        return Math.random() > 0.3;
    }

    private double executeTransactionSumQuery(UUID userId, String productType, String transactionType) {
        return Math.random() * 100000;
    }
}

