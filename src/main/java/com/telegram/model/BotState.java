package com.telegram.model;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BotState {
  NONE,
  START;

  @JsonCreator
  public static Color decode(final String color) {
    return Stream.of(Color.values())
        .filter(targetEnum -> targetEnum.equals(Color.valueOf(color.toUpperCase()))).findFirst()
        .orElse(null);
  }
}
