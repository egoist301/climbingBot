package com.telegram.bot.command;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public enum CommandType {
  CANCEL("cancel"),
  ADD("save route"),
  EDIT("edit route"),
  DELETE("delete route"),
  GET_ALL("get all"),
  GET_ALL_BY_DATE("get all by date"),
  GET_ALL_BY_COLOR("get all by color"),
  WORK_WITH_ROUTE("work with route"),
  VIEW_ROUTES("view routes");

  private final String text;

  CommandType(String text) {
    this.text = text;
  }

  public InlineKeyboardButton getButton() {
    InlineKeyboardButton button = new InlineKeyboardButton();
    button.setText(text);
    button.setCallbackData(this.name());
    return button;
  }
}
