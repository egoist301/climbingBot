package com.telegram.bot.command.impl.view.color;

import com.telegram.bot.command.impl.view.GetAllBaseCommand;
import com.telegram.controller.dto.RouteSearchCriteriaDto;
import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.model.Color;
import com.telegram.service.RouteService;
import com.telegram.service.builder.RouteMessageBuilder;

public class GetAllByRedCommand extends GetAllBaseCommand {
  public GetAllByRedCommand(RouteService routeService, RouteMessageBuilder routeMessageBuilder) {
    super(routeService, routeMessageBuilder);
  }

  @Override
  protected RouteSearchCriteriaDto buildRouteSearchCriteriaDto(TelegramUserDto userDto) {
    RouteSearchCriteriaDto criteriaDto = new RouteSearchCriteriaDto();
    criteriaDto.setUserId(userDto.getId());
    criteriaDto.setColor(Color.RED);
    return criteriaDto;
  }
}
