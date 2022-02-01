package com.telegram.bot.command.impl.view;

import static com.telegram.bot.command.CommandType.CANCEL;
import static com.telegram.model.Color.BLUE;
import static com.telegram.model.Color.GREEN;
import static com.telegram.model.Color.ORANGE;
import static com.telegram.model.Color.PURPLE;
import static com.telegram.model.Color.RED;
import static com.telegram.model.Color.WHITE;
import static com.telegram.model.Color.YELLOW;

import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.telegram.bot.command.Command;
import com.telegram.controller.dto.TelegramUserDto;

public class GetAllByColorCommand implements Command {
  @Override
  public SendMessage execute(TelegramUserDto userDto) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setText("Get all by color");
    sendMessage.setReplyMarkup(buildAvailableCommands());
    return sendMessage;
  }

  private ReplyKeyboard buildAvailableCommands() {
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<InlineKeyboardButton> firstRow = List.of(WHITE.getButton(), YELLOW.getButton(), ORANGE.getButton(), GREEN.getButton());
    List<InlineKeyboardButton> secondRow = List.of(BLUE.getButton(), RED.getButton(), PURPLE.getButton());
    List<List<InlineKeyboardButton>> rowList = List.of(firstRow, secondRow, List.of(CANCEL.getButton()));
    inlineKeyboardMarkup.setKeyboard(rowList);
    return inlineKeyboardMarkup;
  }
}
