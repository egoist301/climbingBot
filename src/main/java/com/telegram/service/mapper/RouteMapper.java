package com.telegram.service.mapper;

import com.telegram.controller.dto.RouteDto;
import com.telegram.model.Route;
import org.modelmapper.ModelMapper;

public class RouteMapper extends AbstractMapper<Route, RouteDto> {
  protected RouteMapper(Class<Route> entityClass, Class<RouteDto> dtoClass, ModelMapper mapper) {
    super(entityClass, dtoClass, mapper);
  }
}
