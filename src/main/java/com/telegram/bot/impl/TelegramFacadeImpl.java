package com.telegram.bot.impl;

import com.telegram.bot.TelegramFacade;
import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class TelegramFacadeImpl implements TelegramFacade {
  private final TelegramUserService userService;

  @Autowired
  public TelegramFacadeImpl(TelegramUserService telegramUserService) {
    this.userService = telegramUserService;
  }

  @Override
  public SendMessage handleUpdate(Update update) {
    Long chatId = getChatIdFromMessage(update);
    String message = getInMessage(update);
    User user = getUserFromMessage(update);
    TelegramUserDto userDto = getUserDto(user, chatId);
    return null;
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
