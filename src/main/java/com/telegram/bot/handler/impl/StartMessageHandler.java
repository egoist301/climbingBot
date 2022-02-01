package com.telegram.bot.handler.impl;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.telegram.bot.handler.MessageHandler;
import com.telegram.bot.handler.MessageHandlerAvailableButtons;
import com.telegram.controller.dto.TelegramUserDto;

public class StartMessageHandler implements MessageHandler {
  @Override
  public SendMessage handle(TelegramUserDto userDto, String text) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setText("Choose operation");
    sendMessage.setReplyMarkup(MessageHandlerAvailableButtons.buildAvailableButtons());
    return sendMessage;
  }
}
