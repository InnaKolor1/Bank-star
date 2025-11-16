package com.project.Bank_star.Repository;

import com.project.Bank_star.Entity.RuleQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleQueryRepository extends JpaRepository<RuleQueryEntity, Long> {
}
