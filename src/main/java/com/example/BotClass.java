package com.example;

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.DefaultLongPollingUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class BotClass extends DefaultLongPollingUpdateConsumer {

    private final TelegramClient telegramClient;

    public BotClass(String botToken) {
        this.telegramClient = new OkHttpTelegramClient(botToken);
    }

    @Override
    public void consume(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }

        long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        String answer = switch (command(text)) {
            case "/start" -> "Этот бот повторяет всё, что ты напишешь. Список команд: /help";
            case "/help" -> """
                    Доступные команды:
                    /start - начать
                    /help - список команд
                    Любой другой текст повторяется""";
            default -> "Пользователь написал: " + text;
        };

        sendText(chatId, answer);
    }

    private static String command(String text) {
        if (!text.startsWith("/")) {
            return "";
        }
        String first = text.split("\\s+", 2)[0];
        int at = first.indexOf('@');
        return at == -1 ? first : first.substring(0, at);
    }

    private void sendText(long chatId, String text) {
        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();
        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}