package com.telegram.service.parser;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class LocalDateMessageParser implements MessageParser<LocalDate> {
  @Override
  public LocalDate parse(String text) {
    return LocalDate.parse(text);
  }
}
