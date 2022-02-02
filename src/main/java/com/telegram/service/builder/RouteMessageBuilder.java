package com.telegram.service.builder;

import java.util.Collection;
import java.util.StringJoiner;

import org.springframework.stereotype.Component;

import com.telegram.controller.dto.RouteDto;

@Component
public class RouteMessageBuilder implements MessageBuilder<RouteDto> {
  @Override
  public String buildMessage(Collection<RouteDto> routeDtos) {
    StringJoiner stringJoiner = new StringJoiner("\n", "Your routes", "Choose operator");
    routeDtos.forEach(routeDto -> stringJoiner.add(routeDto.toString()));
    return stringJoiner.toString();
  }
}
