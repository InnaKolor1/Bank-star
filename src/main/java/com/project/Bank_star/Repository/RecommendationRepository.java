package com.project.Bank_star.Repository;

import com.project.Bank_star.Entity.DynamicRuleEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;



import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RecommendationRepository extends JpaRepository<DynamicRuleEntity, UUID> {
    void clearCache();

    Double getTransactionSum(UUID userId, String s, String withdraw);

    boolean isActiveUserOfProductType(UUID userId, String s);

    boolean isUserOfProductType(UUID userId, String s);
}

