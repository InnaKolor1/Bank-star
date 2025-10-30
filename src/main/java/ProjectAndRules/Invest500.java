package ProjectAndRules;

import com.project.Bank_star.Model.UserFinancial;
import com.project.Bank_star.Recommendation.Recommendation001;
import com.project.Bank_star.Service.RecommendationRuleSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class Invest500 implements RecommendationRuleSet {
    private static final Logger log = LoggerFactory.getLogger(Invest500.class);

    private static final UUID PRODUCT_ID = UUID.fromString("147f6a0f-3b91-413b-ab99-87f081d60d5a");
    private static final String NAME = "Invest 500";
    private static final String TEXT = "Откройте свой путь к успеху с индивидуальным инвестиционным счетом (ИИС) от нашего банка! " +
            "Воспользуйтесь налоговыми льготами и начните инвестировать с умом. " +
            "Пополните счет до конца года и получите выгоду в виде вычета на взнос в следующем налоговом периоде. " +
            "Не упустите возможность разнообразить свой портфель, снизить риски и следить за актуальными рыночными тенденциями. " +
            "Откройте ИИС сегодня и станьте ближе к финансовой независимости!";

    @Override
    public Optional<Recommendation001> applyRuleSet(UUID userId, UserFinancial metrics) {
        boolean hasDebit = metrics.getDebitProducts() != null && metrics.getDebitProducts() > 0;
        boolean noInvest = metrics.getInvestProducts() == null || metrics.getInvestProducts() == 0;
        boolean saving1000 = metrics.getSumSavingDeposits() != null && metrics.getSumSavingDeposits() > 100;

        log.trace("Invest500 for user {}: hasDebit={}, noInvest={}, saving1000={}",
                userId, hasDebit, noInvest, saving1000);

        if (hasDebit && noInvest && saving1000) {
            Recommendation001 rec = new Recommendation001(PRODUCT_ID, NAME, TEXT);
            log.info("Invest500 matched for user {} -> {}", userId, rec.getName());
            return Optional.of(rec);
        }

        return Optional.empty();
    }
}