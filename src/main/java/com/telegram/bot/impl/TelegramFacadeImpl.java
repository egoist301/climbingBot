package com.telegram.bot.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import com.telegram.bot.TelegramFacade;
import com.telegram.bot.command.factory.CommandFactory;
import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.service.TelegramUserService;

@Service
public class TelegramFacadeImpl implements TelegramFacade {
  private final TelegramUserService userService;
  private final CommandFactory commandFactory;

  @Autowired
  public TelegramFacadeImpl(TelegramUserService telegramUserService, CommandFactory commandFactory) {
    this.userService = telegramUserService;
    this.commandFactory = commandFactory;
  }

  @Override
  public SendMessage handleUpdate(Update update) {
    Long chatId = null;
    String message = null;
    User user = null;
    if (update.hasCallbackQuery()) {
      chatId = update.getCallbackQuery().getMessage().getChatId();
      message = update.getCallbackQuery().getData();
      user = update.getCallbackQuery().getFrom();
    } else if (update.hasEditedMessage() && update.hasMessage()) {
      chatId = getChatIdFromMessage(update);
      message = getInMessage(update);
      user = getUserFromMessage(update);
    }
    TelegramUserDto userDto = getUserDto(user, chatId);
    userService.update(userDto);
    return commandFactory.getCommand(message).execute(userDto);
  }

  private String getInMessage(Update update) {
    return update.hasEditedMessage() ?
        update.getEditedMessage().getText() :
        update.getMessage().getText();
  }

  private Long getChatIdFromMessage(Update update) {
    return update.hasEditedMessage() ?
        update.getEditedMessage().getChatId() :
        update.getMessage().getChatId();
  }

  private User getUserFromMessage(Update update) {
    return update.hasEditedMessage() ?
        update.getEditedMessage().getFrom() :
        update.getMessage().getFrom();
  }

  private TelegramUserDto getUserDto(User user, Long chatId) {
    TelegramUserDto userDto = userService.findByChatId(chatId);
    if (userDto == null) {
      userDto = userService.save(new TelegramUserDto(user, chatId));
    }
    return userDto;
  }
}
