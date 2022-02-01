package com.telegram.model;

import java.util.stream.Stream;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Color {
  WHITE,
  YELLOW,
  ORANGE,
  GREEN,
  BLUE,
  RED,
  PURPLE;

  @JsonCreator
  public static Color decode(final String color) {
    return Stream.of(Color.values())
        .filter(targetEnum -> targetEnum.equals(Color.valueOf(color.toUpperCase()))).findFirst()
        .orElse(null);
  }
}
