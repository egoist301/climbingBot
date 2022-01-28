package com.telegram.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum Color {
  RED,
  GREEN,
  WHITE,
  YELLOW,
  BLUE,
  PURPLE;

  @JsonCreator
  public static Color decode(final String color) {
    return Stream.of(Color.values())
        .filter(targetEnum -> targetEnum.equals(Color.valueOf(color.toUpperCase()))).findFirst()
        .orElse(null);
  }
}
