package com.telegram.bot.command.impl.view.color;

import com.telegram.bot.command.impl.view.GetAllBaseCommand;
import com.telegram.controller.dto.RouteSearchCriteriaDto;
import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.model.Color;
import com.telegram.service.RouteService;

public class GetAllByGreenCommand extends GetAllBaseCommand {
  public GetAllByGreenCommand(RouteService routeService) {
    super(routeService);
  }

  @Override
  protected RouteSearchCriteriaDto buildRouteSearchCriteriaDto(TelegramUserDto userDto) {
    RouteSearchCriteriaDto criteriaDto = new RouteSearchCriteriaDto();
    criteriaDto.setUserId(userDto.getId());
    criteriaDto.setColor(Color.GREEN);
    return criteriaDto;
  }
}
