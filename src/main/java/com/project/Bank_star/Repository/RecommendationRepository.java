package com.project.Bank_star.Repository;

import com.project.Bank_star.Entity.DynamicRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecommendationRepository extends JpaRepository<DynamicRuleEntity, UUID> {
}