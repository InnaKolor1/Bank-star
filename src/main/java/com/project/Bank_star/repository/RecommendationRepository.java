package com.project.Bank_star.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class RecommendationRepository {

    private final JdbcTemplate jdbcTemplate;

    public RecommendationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isUserOfProductType(UUID userId, String productType) {
        String sql = "SELECT COUNT(*) > 0 FROM user_products WHERE user_id = ? AND product_type = ?";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, userId, productType));
    }

    public boolean isActiveUserOfProductType(UUID userId, String productType) {
        String sql = "SELECT COUNT(*) > 0 FROM user_products WHERE user_id = ? AND product_type = ? AND status = 'ACTIVE'";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, userId, productType));
    }

    public Double getTransactionSum(UUID userId, String productType, String transactionType) {
        String sql = "SELECT COALESCE(SUM(amount), 0) FROM transactions WHERE user_id = ? AND product_type = ? AND transaction_type = ?";
        return jdbcTemplate.queryForObject(sql, Double.class, userId, productType, transactionType);
    }

    public void clearCache() {
    }
}