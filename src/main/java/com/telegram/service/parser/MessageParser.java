package com.telegram.service.parser;

public interface MessageParser<T> {
  T parse(String text);
}
