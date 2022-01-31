package com.telegram.bot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.telegram.controller.dto.TelegramUserDto;

@FunctionalInterface
public interface Command {
  SendMessage execute(TelegramUserDto userDto);
}
