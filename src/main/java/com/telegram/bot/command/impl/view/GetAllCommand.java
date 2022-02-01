package com.telegram.bot.command.impl.view;

import com.telegram.controller.dto.RouteSearchCriteriaDto;
import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.service.RouteService;

public class GetAllCommand extends GetAllBaseCommand {
  public GetAllCommand(RouteService routeService) {
    super(routeService);
  }

  @Override
  protected RouteSearchCriteriaDto buildRouteSearchCriteriaDto(TelegramUserDto userDto) {
    RouteSearchCriteriaDto criteriaDto = new RouteSearchCriteriaDto();
    criteriaDto.setUserId(userDto.getId());
    return criteriaDto;
  }
}
