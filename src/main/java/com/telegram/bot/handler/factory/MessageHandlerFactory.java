package com.telegram.bot.handler.factory;

import com.telegram.bot.handler.MessageHandler;
import com.telegram.model.BotState;

@FunctionalInterface
public interface MessageHandlerFactory {
  MessageHandler getHandler(BotState state);
}
