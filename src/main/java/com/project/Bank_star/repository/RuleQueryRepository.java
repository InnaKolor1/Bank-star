package com.project.Bank_star.repository;

import com.project.Bank_star.entity.RuleQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleQueryRepository extends JpaRepository<RuleQueryEntity, Long> {
}
