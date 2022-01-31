package com.telegram.bot.command.impl.view;

import static com.telegram.bot.command.CommandType.CANCEL;
import static com.telegram.bot.command.CommandType.GET_ALL;
import static com.telegram.bot.command.CommandType.GET_ALL_BY_DATE;
import static com.telegram.bot.command.CommandType.SELECT_COLOR;

import java.util.Collections;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.telegram.bot.command.Command;
import com.telegram.controller.dto.TelegramUserDto;

public class ViewRoutesCommand implements Command {
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
    List<InlineKeyboardButton> firstRow = List.of(GET_ALL.getButton(), GET_ALL_BY_DATE.getButton(), SELECT_COLOR.getButton());
    List<InlineKeyboardButton> secondRow = Collections.singletonList(CANCEL.getButton());
    inlineKeyboardMarkup.setKeyboard(List.of(firstRow, secondRow));
    return inlineKeyboardMarkup;
  }
}
