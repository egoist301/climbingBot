package com.telegram.bot.command.impl.view;

import com.telegram.controller.dto.RouteSearchCriteriaDto;
import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.service.RouteService;
import com.telegram.service.builder.RouteMessageBuilder;

public class GetAllCommand extends GetAllBaseCommand {
  public GetAllCommand(RouteService routeService, RouteMessageBuilder routeMessageBuilder) {
    super(routeService, routeMessageBuilder);
  }

  @Override
  protected RouteSearchCriteriaDto buildRouteSearchCriteriaDto(TelegramUserDto userDto) {
    RouteSearchCriteriaDto criteriaDto = new RouteSearchCriteriaDto();
    criteriaDto.setUserId(userDto.getId());
    return criteriaDto;
  }
}
