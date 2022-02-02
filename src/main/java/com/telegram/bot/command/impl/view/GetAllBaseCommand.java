package com.telegram.bot.command.impl.view;

import static com.telegram.bot.command.CommandType.VIEW_ROUTES;
import static com.telegram.bot.command.CommandType.WORK_WITH_ROUTE;

import java.util.List;
import java.util.Set;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.telegram.bot.command.Command;
import com.telegram.controller.dto.RouteDto;
import com.telegram.controller.dto.RouteSearchCriteriaDto;
import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.model.BotState;
import com.telegram.service.RouteService;
import com.telegram.service.builder.RouteMessageBuilder;

public abstract class GetAllBaseCommand implements Command {
  private final RouteService routeService;
  private final RouteMessageBuilder routeMessageBuilder;

  protected GetAllBaseCommand(RouteService routeService, RouteMessageBuilder routeMessageBuilder) {
    this.routeService = routeService;
    this.routeMessageBuilder = routeMessageBuilder;
  }

  @Override
  public SendMessage execute(TelegramUserDto userDto) {
    Set<RouteDto> routeDto = routeService.findAll(buildRouteSearchCriteriaDto(userDto));
    SendMessage sendMessage = new SendMessage();
    sendMessage.setText(routeMessageBuilder.buildMessage(routeDto));
    sendMessage.setReplyMarkup(buildAvailableCommands());
    userDto.setBotState(BotState.START);
    return sendMessage;
  }

  protected abstract RouteSearchCriteriaDto buildRouteSearchCriteriaDto(TelegramUserDto userDto);

  private ReplyKeyboard buildAvailableCommands() {
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<InlineKeyboardButton> row = List.of(VIEW_ROUTES.getButton(), WORK_WITH_ROUTE.getButton());
    List<List<InlineKeyboardButton>> rowList = List.of(row);
    inlineKeyboardMarkup.setKeyboard(rowList);
    return inlineKeyboardMarkup;
  }
}
