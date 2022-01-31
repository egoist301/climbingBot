package com.telegram.bot.command.impl.view;

import static com.telegram.bot.command.CommandType.VIEW_ROUTES;
import static com.telegram.bot.command.CommandType.WORK_WITH_ROUTE;

import java.util.List;
import java.util.StringJoiner;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.telegram.bot.command.Command;
import com.telegram.controller.dto.RouteDto;
import com.telegram.controller.dto.RouteSearchCriteriaDto;
import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.service.RouteService;

public class GetAllCommand implements Command {
  private final RouteService routeService;

  public GetAllCommand(RouteService routeService) {
    this.routeService = routeService;
  }

  @Override
  public SendMessage execute(TelegramUserDto userDto) {
    RouteSearchCriteriaDto criteriaDto = new RouteSearchCriteriaDto();
    criteriaDto.setUserId(userDto.getId());
    List<RouteDto> routeDto = routeService.findAll(criteriaDto);

    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(userDto.getChatId().toString());
    sendMessage.setText(buildMessage(routeDto));
    sendMessage.enableMarkdown(true);
    sendMessage.setReplyMarkup(buildAvailableCommands());
    return sendMessage;
  }

  private String buildMessage(List<RouteDto> routeDtos) {
    StringJoiner stringJoiner = new StringJoiner("Your routes", "\n", "Choose operator");
    routeDtos.forEach(routeDto -> stringJoiner.add(routeDto.toString()));
    return stringJoiner.toString();
  }

  private ReplyKeyboard buildAvailableCommands() {
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<InlineKeyboardButton> row = List.of(VIEW_ROUTES.getButton(), WORK_WITH_ROUTE.getButton());
    List<List<InlineKeyboardButton>> rowList = List.of(row);
    inlineKeyboardMarkup.setKeyboard(rowList);
    return inlineKeyboardMarkup;
  }
}
