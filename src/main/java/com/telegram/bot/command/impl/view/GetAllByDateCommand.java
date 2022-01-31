package com.telegram.bot.command.impl.view;

import static com.telegram.bot.command.CommandType.CANCEL;

import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.telegram.bot.command.Command;
import com.telegram.controller.dto.TelegramUserDto;

public class GetAllByDateCommand implements Command {
  @Override
  public SendMessage execute(TelegramUserDto userDto) {

    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(userDto.getChatId().toString());
    sendMessage.setText("Enter date");
    sendMessage.enableMarkdown(true);
    sendMessage.setReplyMarkup(buildAvailableCommands());
    return sendMessage;
  }

  private ReplyKeyboard buildAvailableCommands() {
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rowList = List.of(List.of(CANCEL.getButton()));
    inlineKeyboardMarkup.setKeyboard(rowList);
    return inlineKeyboardMarkup;
  }
}
