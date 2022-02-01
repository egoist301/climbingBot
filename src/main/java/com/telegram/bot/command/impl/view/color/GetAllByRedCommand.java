package com.telegram.bot.command.impl.view.color;

import com.telegram.bot.command.impl.view.GetAllBaseCommand;
import com.telegram.controller.dto.RouteSearchCriteriaDto;
import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.model.Color;
import com.telegram.service.RouteService;

public class GetAllByRedCommand extends GetAllBaseCommand {
  public GetAllByRedCommand(RouteService routeService) {
    super(routeService);
  }

  @Override
  protected RouteSearchCriteriaDto buildRouteSearchCriteriaDto(TelegramUserDto userDto) {
    RouteSearchCriteriaDto criteriaDto = new RouteSearchCriteriaDto();
    criteriaDto.setUserId(userDto.getId());
    criteriaDto.setColor(Color.RED);
    return criteriaDto;
  }
}
