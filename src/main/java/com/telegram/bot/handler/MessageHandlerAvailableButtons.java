package com.telegram.bot.handler;

import static com.telegram.bot.command.CommandType.VIEW_ROUTES;
import static com.telegram.bot.command.CommandType.WORK_WITH_ROUTE;

import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public final class MessageHandlerAvailableButtons {
  private MessageHandlerAvailableButtons() {
  }

  public static ReplyKeyboard buildAvailableButtons() {
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<InlineKeyboardButton> row = List.of(VIEW_ROUTES.getButton(), WORK_WITH_ROUTE.getButton());
    List<List<InlineKeyboardButton>> rowList = List.of(row);
    inlineKeyboardMarkup.setKeyboard(rowList);
    return inlineKeyboardMarkup;
  }
}
