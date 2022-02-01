package com.telegram.bot.command.impl.view;

import static com.telegram.bot.command.CommandType.CANCEL;
import static com.telegram.bot.command.CommandType.GET_ALL_BY_BLUE;
import static com.telegram.bot.command.CommandType.GET_ALL_BY_GREEN;
import static com.telegram.bot.command.CommandType.GET_ALL_BY_ORANGE;
import static com.telegram.bot.command.CommandType.GET_ALL_BY_PURPLE;
import static com.telegram.bot.command.CommandType.GET_ALL_BY_RED;
import static com.telegram.bot.command.CommandType.GET_ALL_BY_WHITE;
import static com.telegram.bot.command.CommandType.GET_ALL_BY_YELLOW;

import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.telegram.bot.command.Command;
import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.model.BotState;

public class GetAllByColorCommand implements Command {
  @Override
  public SendMessage execute(TelegramUserDto userDto) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setText("Get all by color");
    sendMessage.setReplyMarkup(buildAvailableCommands());
    userDto.setBotState(BotState.GET_ALL_BY_COLOR);
    return sendMessage;
  }

  private ReplyKeyboard buildAvailableCommands() {
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<InlineKeyboardButton> firstRow = List.of(GET_ALL_BY_WHITE.getButton(),
        GET_ALL_BY_YELLOW.getButton(), GET_ALL_BY_ORANGE.getButton(), GET_ALL_BY_GREEN.getButton());
    List<InlineKeyboardButton> secondRow = List.of(GET_ALL_BY_BLUE.getButton(),
        GET_ALL_BY_RED.getButton(), GET_ALL_BY_PURPLE.getButton());
    List<List<InlineKeyboardButton>> rowList = List.of(firstRow, secondRow, List.of(CANCEL.getButton()));
    inlineKeyboardMarkup.setKeyboard(rowList);
    return inlineKeyboardMarkup;
  }
}
