package com.telegram.bot.handler.impl;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.telegram.bot.handler.MessageHandler;
import com.telegram.bot.handler.MessageHandlerAvailableButtons;
import com.telegram.controller.dto.RouteDto;
import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.service.RouteService;
import com.telegram.service.parser.RouteMessageParser;

public class AddRouteMessageHandler implements MessageHandler {
  private final RouteMessageParser messageParser;
  private final RouteService routeService;

  public AddRouteMessageHandler(RouteMessageParser messageParser, RouteService routeService) {
    this.messageParser = messageParser;
    this.routeService = routeService;
  }

  @Override
  public SendMessage handle(TelegramUserDto userDto, String text) {
    RouteDto routeDto = messageParser.parse(text);
    routeDto.setUserDto(userDto);
    SendMessage sendMessage = new SendMessage();
    sendMessage.setReplyMarkup(MessageHandlerAvailableButtons.buildAvailableButtons());
    sendMessage.setText(routeService.save(routeDto).toString());
    return sendMessage;
  }
}
