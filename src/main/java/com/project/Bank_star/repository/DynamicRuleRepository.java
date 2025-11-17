package com.project.Bank_star.repository;

import com.project.Bank_star.entity.DynamicRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DynamicRuleRepository extends JpaRepository<DynamicRuleEntity, UUID> {

    @Query("SELECT d FROM DynamicRuleEntity d LEFT JOIN FETCH d.rule WHERE d.productId = :productId")
    Optional<DynamicRuleEntity> findByProductIdWithRules(@Param("productId") UUID productId);

    boolean existsByProductId(UUID productId);

    @Modifying
    @Transactional
    void deleteByProductId(UUID productId);
}

