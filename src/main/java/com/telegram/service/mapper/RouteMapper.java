package com.telegram.service.mapper;

import com.telegram.controller.dto.RouteDto;
import com.telegram.model.Route;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteMapper extends AbstractMapper<Route, RouteDto> {
  @Autowired
  public RouteMapper(ModelMapper mapper) {
    super(Route.class, RouteDto.class, mapper);
  }
}
