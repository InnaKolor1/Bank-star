package com.project.Bank_star.Repository;

import com.project.Bank_star.Entity.DynamicRuleEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;



import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RecommendationRepository extends JpaRepository<DynamicRuleEntity, UUID> {

    @Modifying
    @Query(value = "SELECT 1", nativeQuery = true)
    void clearCache();

    @Query(value = "SELECT COALESCE(SUM(amount), 0.0) FROM transactions WHERE user_id = :userId AND product_type = :productType AND transaction_type = :transactionType", nativeQuery = true)
    Double getTransactionSum(@Param("userId") UUID userId, @Param("productType") String productType, @Param("transactionType") String transactionType);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM user_products WHERE user_id = :userId AND product_type = :productType AND is_active = true", nativeQuery = true)
    boolean isActiveUserOfProductType(@Param("userId") UUID userId, @Param("productType") String productType);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM user_products WHERE user_id = :userId AND product_type = :productType", nativeQuery = true)
    boolean isUserOfProductType(@Param("userId") UUID userId, @Param("productType") String productType);
}


