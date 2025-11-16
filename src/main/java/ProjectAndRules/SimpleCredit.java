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
public class SimpleCredit implements RecommendationRuleSet {

    private static final Logger logger = LoggerFactory.getLogger(SimpleCredit.class);
    private static final UUID PRODUCT_ID = UUID.fromString("ab138afb-f3ba-4a93-b74f-0fcee86d447f");
    private static final String NAME = "Simple Credit";
    private static final String TEXT = "Откройте мир выгодных кредитов с нами!" + "Ищете способ быстро и без лишних хлопот получить нужную сумму? " +
            "Тогда наш выгодный кредит — именно то, что вам нужно!" +
            "Мы предлагаем низкие процентные ставки, гибкие условия и индивидуальный подход к каждому клиенту." +
            "Почему выбирают нас:" +
            "Быстрое рассмотрение заявки. Мы ценим ваше время, поэтому процесс рассмотрения заявки занимает всего несколько часов" +
            "Удобное оформление. Подать заявку на кредит можно онлайн на нашем сайте или в мобильном приложении" +
            "Широкий выбор кредитных продуктов. Мы предлагаем кредиты на различные цели: покупку недвижимости, автомобиля, образование, лечение и многое другое." +
            "Не упустите возможность воспользоваться выгодными условиями кредитования от нашей компании!";


    @Override
    public Optional<Recommendation001> applyRuleSet(UUID userId, UserFinancial metrics) {
        logger.info("Применение правила SimpleCreditRule для пользователя {}", userId);

        if (metrics.getCntCreditProducts() == 0 &&
                metrics.getSumDebitDeposits() > 0) {
            logger.info("Рекомендован кредитный продукт: Простой кредит для пользователя {}", userId);
            return Optional.of(new Recommendation001(
                    PRODUCT_ID,
                    "Простой кредит",
                    "Откройте мир выгодных кредитов с нами!"
            ));
        }
        logger.info("Правило SimpleCreditRule не сработало для пользователя {}", userId);
        return Optional.empty();
        }
}

