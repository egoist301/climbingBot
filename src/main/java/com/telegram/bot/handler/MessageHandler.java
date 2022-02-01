package com.telegram.bot.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.telegram.controller.dto.TelegramUserDto;

@FunctionalInterface
public interface MessageHandler {
  SendMessage handle(TelegramUserDto userDto, String text);
}
