package com.telegram.bot.handler.impl;

import java.time.LocalDate;
import java.util.Set;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.telegram.bot.handler.MessageHandler;
import com.telegram.bot.handler.MessageHandlerAvailableButtons;
import com.telegram.controller.dto.RouteDto;
import com.telegram.controller.dto.RouteSearchCriteriaDto;
import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.model.BotState;
import com.telegram.service.RouteService;
import com.telegram.service.builder.RouteMessageBuilder;
import com.telegram.service.parser.LocalDateMessageParser;

public class GetAllByDateMessageHandler implements MessageHandler {
  private final LocalDateMessageParser localDateMessageParser;
  private final RouteService routeService;
  private final RouteMessageBuilder routeMessageBuilder;

  public GetAllByDateMessageHandler(LocalDateMessageParser localDateMessageParser, RouteService routeService, RouteMessageBuilder routeMessageBuilder) {
    this.localDateMessageParser = localDateMessageParser;
    this.routeService = routeService;
    this.routeMessageBuilder = routeMessageBuilder;
  }

  @Override
  public SendMessage handle(TelegramUserDto userDto, String text) {
    LocalDate created = localDateMessageParser.parse(text);
    Set<RouteDto> routeDto = routeService.findAll(buildRouteSearchCriteriaDto(userDto, created));
    SendMessage sendMessage = new SendMessage();
    sendMessage.setReplyMarkup(MessageHandlerAvailableButtons.buildAvailableButtons());
    sendMessage.setText(routeMessageBuilder.buildMessage(routeDto));
    userDto.setBotState(BotState.START);
    return sendMessage;
  }

  private RouteSearchCriteriaDto buildRouteSearchCriteriaDto(TelegramUserDto userDto, LocalDate created) {
    RouteSearchCriteriaDto criteriaDto = new RouteSearchCriteriaDto();
    criteriaDto.setUserId(userDto.getId());
    criteriaDto.setCreated(created);
    return criteriaDto;
  }
}
