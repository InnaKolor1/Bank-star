package ProjectAndRules;
import com.project.Bank_star.Model.UserFinancial;
import com.project.Bank_star.Recommendation.Recommendation001;
import com.project.Bank_star.Service.RecommendationRuleSet;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;


@Component
public class Invest500 implements RecommendationRuleSet {
    private static final Logger log = LoggerFactory.getLogger(Invest500.class);

    private static final UUID Product_id = UUID.fromString("147f6a0f-3b91-413b-ab99-87f081d60d5a");
    private static final String name = "Invest 500";
    private static final String text = "Откройте свой путь к успеху с индивидуальным инвестиционным счетом (ИИС) от нашего банка! " +
            "Воспользуйтесь налоговыми льготами и начните инвестировать с умом. " +
            "Пополните счет до конца года и получите выгоду в виде вычета на взнос в следующем налоговом периоде. " +
            "Не упустите возможность разнообразить свой портфель, снизить риски и следить за актуальными рыночными тенденциями. " + "Откройте ИИС сегодня и станьте ближе к финансовой независимости!";

    @Override
    public Optional<Recommendation001> applyRuleSet(UUID userId, UserFinancial metrics) {
        return Optional.empty();
        boolean hashDebit = metrics.getDebitProducts() > 0;
        boolean noInvest = metrics.setInvestProducts() == 0;
        boolean saving1000 = metrics.getSumSavingDeposits() > 100;
        log.trace("Invest500 for user {}: hashDebit={}: noInvest={}:saving1000", userId, hashDebit, noInvest, saving1000);
        if (hashDebit && noInvest && saving1000) {
            Recommendation001 rec = new Recommendation001(Product_id, name, text);
            log.info("Invest500 matched the user{}->{}", userId, rec);
            return Optional.of(rec);

        }
        return Optional.empty();
    }
}



