package ProjectAndRules;

import com.project.Bank_star.Model.UserFinancial;
import com.project.Bank_star.recommendation.Recommendation001;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class Invest500Tests {

    private final Invest500 invest500 = new Invest500();

    @Test
    void applyRuleSet_WhenConditionsMet_ShouldReturnRecommendation() {

        UUID userId = UUID.randomUUID();
        UserFinancial metrics = new UserFinancial(1L, 0L, 1000.0);


        Optional<Recommendation001> result = invest500.applyRuleSet(userId, metrics);


        assertTrue(result.isPresent());
        assertEquals("Invest 500", result.get().getName());
    }

    @Test
    void applyRuleSet_WhenNoDebitProducts_ShouldReturnEmpty() {

        UUID userId = UUID.randomUUID();
        UserFinancial metrics = new UserFinancial(0L, 0L, 1000.0);


        Optional<Recommendation001> result = invest500.applyRuleSet(userId, metrics);


        assertFalse(result.isPresent());
    }

    @Test
    void applyRuleSet_WhenHasInvestProducts_ShouldReturnEmpty() {

        UUID userId = UUID.randomUUID();
        UserFinancial metrics = new UserFinancial(1L, 1L, 1000.0);


        Optional<Recommendation001> result = invest500.applyRuleSet(userId, metrics);


        assertFalse(result.isPresent());
    }

    @Test
    void applyRuleSet_WhenLowSavings_ShouldReturnEmpty() {

        UUID userId = UUID.randomUUID();
        UserFinancial metrics = new UserFinancial(1L, 0L, 50.0);


        Optional<Recommendation001> result = invest500.applyRuleSet(userId, metrics);


        assertFalse(result.isPresent());
    }
}