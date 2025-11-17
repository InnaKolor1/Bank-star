package com.project.Bank_star.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UUID> findUserIdsByUsername(String username) {
        String sql = "SELECT id FROM users WHERE username ILIKE ?";
        return jdbcTemplate.queryForList(sql, UUID.class, "%" + username + "%");
    }

    public String getUserName(UUID userId) {
        String sql = "SELECT first_name, last_name FROM users WHERE id = ?";
        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                return (firstName != null ? firstName + " " : "") +
                        (lastName != null ? lastName : "");
            }
            return "Пользователь";
        }, userId.toString());
    }
}
