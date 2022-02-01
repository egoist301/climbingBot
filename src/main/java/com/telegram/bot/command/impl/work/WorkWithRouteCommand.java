package com.telegram.bot.command.impl.work;

import static com.telegram.bot.command.CommandType.ADD;
import static com.telegram.bot.command.CommandType.CANCEL;
import static com.telegram.bot.command.CommandType.DELETE;
import static com.telegram.bot.command.CommandType.EDIT;

import java.util.Collections;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.telegram.bot.command.Command;
import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.model.BotState;

public class WorkWithRouteCommand implements Command {
  @Override
  public SendMessage execute(TelegramUserDto userDto) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setText("Choose operation");
    sendMessage.setReplyMarkup(buildAvailableCommands());
    userDto.setBotState(BotState.WORK);
    return sendMessage;
  }

  private ReplyKeyboard buildAvailableCommands() {
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<InlineKeyboardButton> firstRow = List.of(ADD.getButton(), EDIT.getButton(), DELETE.getButton());
    List<InlineKeyboardButton> secondRow = Collections.singletonList(CANCEL.getButton());
    inlineKeyboardMarkup.setKeyboard(List.of(firstRow, secondRow));
    return inlineKeyboardMarkup;
  }
}
