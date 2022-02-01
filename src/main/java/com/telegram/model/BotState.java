package com.telegram.model;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BotState {
  START,
  VIEW,
  WORK,
  ADD,
  EDIT,
  DELETE,
  GET_ALL_BY_DATE,
  GET_ALL_BY_COLOR;

  @JsonCreator
  public static Color decode(final String color) {
    return Stream.of(Color.values())
        .filter(targetEnum -> targetEnum.equals(Color.valueOf(color.toUpperCase()))).findFirst()
        .orElse(null);
  }
}
