package com.telegram.bot.command.impl;

import static com.telegram.bot.command.CommandType.VIEW_ROUTES;
import static com.telegram.bot.command.CommandType.WORK_WITH_ROUTE;

import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.telegram.bot.command.Command;
import com.telegram.controller.dto.TelegramUserDto;

public class CancelCommand implements Command {
  @Override
  public SendMessage execute(TelegramUserDto userDto) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(userDto.getChatId().toString());
    sendMessage.setText("Choose operation");
    sendMessage.enableMarkdown(true);
    sendMessage.setReplyMarkup(buildAvailableCommands());
    return sendMessage;
  }

  private ReplyKeyboard buildAvailableCommands() {
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<InlineKeyboardButton> row = List.of(VIEW_ROUTES.getButton(), WORK_WITH_ROUTE.getButton());
    List<List<InlineKeyboardButton>> rowList = List.of(row);
    inlineKeyboardMarkup.setKeyboard(rowList);
    return inlineKeyboardMarkup;
  }
}
