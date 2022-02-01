package com.telegram.bot.handler.factory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.telegram.bot.handler.MessageHandler;
import com.telegram.bot.handler.impl.AddRouteMessageHandler;
import com.telegram.bot.handler.impl.StartMessageHandler;
import com.telegram.model.BotState;
import com.telegram.service.RouteService;
import com.telegram.service.parser.RouteMessageParser;

@Component
public class MessageHandlerFactoryImpl implements MessageHandlerFactory {
  private static final Map<BotState, MessageHandler> HANDLERS = new HashMap<>();
  private final RouteMessageParser routeMessageParser;
  private final RouteService routeService;

  public MessageHandlerFactoryImpl(RouteMessageParser routeMessageParser, RouteService routeService) {
    this.routeMessageParser = routeMessageParser;
    this.routeService = routeService;
  }

  @Override
  public MessageHandler getHandler(BotState state) {
    if (HANDLERS.isEmpty()) {
      HANDLERS.put(BotState.START, new StartMessageHandler());
      HANDLERS.put(BotState.ADD, new AddRouteMessageHandler(routeMessageParser, routeService));
    }
    return HANDLERS.get(state);
  }
}
