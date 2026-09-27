package com.example;

import io.github.cdimascio.dotenv.Dotenv;

public final class Config {

    private final String botToken;

    private Config(String botToken) {
        this.botToken = botToken;
    }

    public static Config load() {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        String token = dotenv.get("BOT_TOKEN");
        if (token == null || token.isBlank()) {
            throw new IllegalStateException("BOT_TOKEN не задан в .env или окружении");
        }
        return new Config(token);
    }

    public String getBotToken() {
        return botToken;
    }
}