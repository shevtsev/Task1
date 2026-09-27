package com.example;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

public class Main {

    public static void main(String[] args) throws Exception {
        Config config = Config.load();

        try (TelegramBotsLongPollingApplication app = new TelegramBotsLongPollingApplication()) {
            app.registerBot(config.getBotToken(), new BotClass(config.getBotToken()));
            System.out.println();
            Thread.currentThread().join();
        }
    }
}