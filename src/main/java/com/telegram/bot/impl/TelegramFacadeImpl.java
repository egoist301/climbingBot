package com.telegram.bot.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import com.telegram.bot.TelegramFacade;
import com.telegram.bot.command.factory.CommandFactory;
import com.telegram.bot.handler.factory.MessageHandlerFactory;
import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.service.TelegramUserService;

@Service
public class TelegramFacadeImpl implements TelegramFacade {
  private final TelegramUserService userService;
  private final CommandFactory commandFactory;
  private final MessageHandlerFactory messageHandlerFactory;

  @Autowired
  public TelegramFacadeImpl(TelegramUserService telegramUserService, CommandFactory commandFactory, MessageHandlerFactory messageHandlerFactory) {
    this.userService = telegramUserService;
    this.commandFactory = commandFactory;
    this.messageHandlerFactory = messageHandlerFactory;
  }

  @Override
  public SendMessage handleUpdate(Update update) {
    Long chatId;
    SendMessage sendMessage;
    TelegramUserDto userDto;
    if (update.hasCallbackQuery()) {
      chatId = update.getCallbackQuery().getMessage().getChatId();
      String message = update.getCallbackQuery().getData();
      User user = update.getCallbackQuery().getFrom();
      userDto = getTelegramUserDto(chatId, user);
      sendMessage = commandFactory.getCommand(message).execute(userDto);
    } else if (update.hasMessage()) {
      chatId = update.getMessage().getChatId();
      String message = update.getMessage().getText();
      User user = update.getMessage().getFrom();
      userDto = getTelegramUserDto(chatId, user);
      sendMessage = messageHandlerFactory.getHandler(userDto.getBotState()).handle(userDto, message);
    } else {
      throw new RuntimeException();
    }
    userService.update(userDto);
    sendMessage.setChatId(chatId.toString());
    sendMessage.enableMarkdown(true);
    return sendMessage;
  }

  private TelegramUserDto getTelegramUserDto(Long chatId, User user) {
    TelegramUserDto userDto = userService.findByChatId(chatId);
    if (userDto == null) {
      userDto = userService.save(new TelegramUserDto(user, chatId));
    }
    return userDto;
  }
}
