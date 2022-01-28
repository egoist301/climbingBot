package com.telegram.service;

import com.telegram.controller.dto.RouteDto;
import com.telegram.controller.dto.RouteSearchCriteriaDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RouteService {
  RouteDto save(RouteDto routeDto);

  RouteDto update(RouteDto routeDto);

  void delete(Long id);

  List<RouteDto> findAll(RouteSearchCriteriaDto criteria);

  RouteDto findById(Long id);
}
