package com.example;

import com.microsoft.bot.builder.ActivityHandler;
import com.microsoft.bot.builder.TurnContext;

import java.util.concurrent.CompletableFuture;

public class BotClass extends ActivityHandler {
    @Override
    protected CompletableFuture<Void> onMessageActivity(TurnContext turnContext) {
        return super.onMessageActivity(turnContext);
    }
}