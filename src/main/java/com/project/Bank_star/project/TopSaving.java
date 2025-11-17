package com.project.Bank_star.project;

import com.project.Bank_star.model.UserFinancial;
import com.project.Bank_star.recommendation.Recommendation001;
import com.project.Bank_star.service.RecommendationRuleSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class TopSaving implements RecommendationRuleSet {
    private static final Logger logger = LoggerFactory.getLogger(TopSaving.class);
    private static final UUID PRODUCT_ID = UUID.fromString("59efc529-2fff-41af-baff-90ccd7402925");
    private static final String NAME = "Top Saving";
    private static final String TEXT = "Максимизируйте свои сбережения с нашим премиальным сберегательным счетом!";

    @Override
    public Optional<Recommendation001> applyRuleSet(UUID userId, UserFinancial metrics) {
        boolean highSavings = metrics.getSumSavingDeposits() != null && metrics.getSumSavingDeposits() > 50000;

        if (highSavings) {
            Recommendation001 rec = new Recommendation001(PRODUCT_ID, NAME, TEXT);
            logger.info("TopSaving matched for user {} -> {}", userId, rec);
            return Optional.of(rec);
        }

        return Optional.empty();
    }
}

