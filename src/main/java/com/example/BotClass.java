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

        SendMessage reply = SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text("Пользователь написал: " + update.getMessage().getText())
                .build();
        try {
            telegramClient.execute(reply);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}