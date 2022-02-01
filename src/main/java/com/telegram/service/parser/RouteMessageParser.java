package com.telegram.service.parser;

import org.springframework.stereotype.Component;

import com.telegram.controller.dto.RouteDto;
import com.telegram.model.Color;

@Component
public class RouteMessageParser implements MessageParser<RouteDto> {
  private static final String DELIMITER = ";";

  @Override
  public RouteDto parse(String text) {
    String[] fields = text.split(DELIMITER);
    RouteDto routeDto = new RouteDto();
    routeDto.setColor(Color.valueOf(fields[0].toUpperCase()));
    routeDto.setAttempt(fields[1]);
    return routeDto;
  }
}
