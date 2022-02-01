package com.telegram.service.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.telegram.controller.dto.RouteDto;
import com.telegram.model.Route;

@Component
public class RouteMapper implements Mapper<Route, RouteDto> {
  private final TelegramUserMapper userMapper;

  public RouteMapper(TelegramUserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public Route toEntity(RouteDto routeDto) {
    if (routeDto == null) {
      return null;
    }
    Route route = new Route();
    route.setAttempt(routeDto.getAttempt());
    route.setColor(routeDto.getColor());
    route.setId(routeDto.getId());
    route.setUser(userMapper.toEntity(routeDto.getUserDto()));
    return route;
  }

  @Override
  public RouteDto toDto(Route route) {
    if (route == null) {
      return null;
    }
    RouteDto routeDto = new RouteDto();
    routeDto.setAttempt(route.getAttempt());
    routeDto.setColor(route.getColor());
    routeDto.setCreated(route.getCreated());
    routeDto.setId(route.getId());
    routeDto.setUserDto(userMapper.toDto(route.getUser()));
    return routeDto;
  }

  @Override
  public Set<RouteDto> toDtoSet(Collection<Route> routes) {
    return routes.stream().map(this::toDto).collect(Collectors.toSet());
  }

  @Override
  public Set<Route> toEntitySet(Collection<RouteDto> dList) {
    return dList.stream().map(this::toEntity).collect(Collectors.toSet());
  }
}
