package com.project.Bank_star.config;

import com.project.Bank_star.service.RecommendationService;
import com.project.Bank_star.service.UserService;
import com.project.Bank_star.telegram.BankRecommendationBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfig {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.name}")
    private String botName;

    @Bean
    public TelegramBotsApi telegramBotsApi(BankRecommendationBot bot) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(bot);
        return botsApi;
    }

    @Bean
    public BankRecommendationBot bankRecommendationBot(RecommendationService recommendationService,
                                                       UserService userService) {
        return new BankRecommendationBot(botToken, botName, recommendationService, userService);
    }
}