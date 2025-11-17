package com.project.Bank_star.telegram;

import com.project.Bank_star.recommendation.Recommendation001;
import com.project.Bank_star.recommendation.RecommendationResponse;
import com.project.Bank_star.service.RecommendationService;
import com.project.Bank_star.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.UUID;

public class BankRecommendationBot extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(BankRecommendationBot.class);
    private final String botName;
    private final RecommendationService recommendationService;
    private final UserService userService;

    public BankRecommendationBot(String botToken, String botName,
                                 RecommendationService recommendationService,
                                 UserService userService) {
        super(botToken);
        this.botName = botName;
        this.recommendationService = recommendationService;
        this.userService = userService;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if (text.equals("/start")) {
                sendHelpMessage(chatId);
            } else if (text.startsWith("/recommend ")) {
                handleRecommendCommand(chatId, text);
            } else {
                sendMessage(chatId, "Неизвестная команда. Используйте /start для справки.");
            }
        }
    }

    private void handleRecommendCommand(Long chatId, String text) {
        try {
            String[] parts = text.split(" ", 2);
            if (parts.length < 2) {
                sendMessage(chatId, "Укажите имя пользователя: /recommend username");
                return;
            }

            String username = parts[1].trim();
            List<UUID> userIds = userService.findUserIdsByUsername(username);

            if (userIds.isEmpty()) {
                sendMessage(chatId, "Пользователь не найден");
            } else if (userIds.size() > 1) {
                sendMessage(chatId, "Найдено несколько пользователей. Уточните запрос.");
            } else {
                UUID userId = userIds.get(0);
                String userName = userService.getUserName(userId);
                RecommendationResponse response = recommendationService.getRecommendations(userId);

                String message = formatRecommendations(userName, response.getRecommendations());
                sendMessage(chatId, message);
            }

        } catch (Exception e) {
            log.error("Error processing recommend command", e);
            sendMessage(chatId, "Произошла ошибка при обработке запроса");
        }
    }

    private String formatRecommendations(String userName, List<Recommendation001> recommendations) {
        StringBuilder sb = new StringBuilder();
        sb.append("Здравствуйте, ").append(userName).append("!\n\n");

        if (recommendations.isEmpty()) {
            sb.append("На данный момент у нас нет специальных предложений для вас.");
        } else {
            sb.append("Рекомендуемые продукты:\n\n");
            for (int i = 0; i < recommendations.size(); i++) {
                Recommendation001 rec = recommendations.get(i);
                sb.append(i + 1).append(". ").append(rec.getName()).append("\n");
                sb.append(rec.getText()).append("\n\n");
            }
        }
        return sb.toString();
    }

    private void sendHelpMessage(Long chatId) {
        String helpText = "Добро пожаловать в банк рекомендаций!\n\n" +
                "Доступные команды:\n" +
                "/recommend username - получить рекомендации для пользователя\n" +
                "/start - показать справку";
        sendMessage(chatId, helpText);
    }

    private void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Failed to send message to {}", chatId, e);
        }
    }
}
