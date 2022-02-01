package com.telegram.bot.command.impl.view;

import static com.telegram.bot.command.CommandType.CANCEL;

import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.telegram.bot.command.Command;
import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.model.BotState;

public class GetAllByDateCommand implements Command {
  @Override
  public SendMessage execute(TelegramUserDto userDto) {

    SendMessage sendMessage = new SendMessage();
    sendMessage.setText("Enter date");
    sendMessage.setReplyMarkup(buildAvailableCommands());
    userDto.setBotState(BotState.GET_ALL_BY_DATE);
    return sendMessage;
  }

  private ReplyKeyboard buildAvailableCommands() {
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rowList = List.of(List.of(CANCEL.getButton()));
    inlineKeyboardMarkup.setKeyboard(rowList);
    return inlineKeyboardMarkup;
  }
}
