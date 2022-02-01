package com.telegram.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.telegram.controller.dto.RouteDto;
import com.telegram.controller.dto.RouteSearchCriteriaDto;

@Service
public interface RouteService {
  RouteDto save(RouteDto routeDto);

  RouteDto update(RouteDto routeDto);

  void delete(Long id);

  Set<RouteDto> findAll(RouteSearchCriteriaDto criteria);

  RouteDto findById(Long id);
}
