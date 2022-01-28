package com.telegram.service.impl;

import com.telegram.controller.dto.RouteDto;
import com.telegram.controller.dto.RouteSearchCriteriaDto;
import com.telegram.model.Route;
import com.telegram.repository.RouteRepository;
import com.telegram.service.RouteService;
import com.telegram.service.exception.ResourceNotFoundException;
import com.telegram.service.mapper.RouteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.telegram.repository.specification.RouteSpecification.findByColor;
import static com.telegram.repository.specification.RouteSpecification.findByCreated;
import static com.telegram.repository.specification.RouteSpecification.findByUserId;

@Service
public class RouteServiceImpl implements RouteService {
  private final RouteRepository routeRepository;
  private final RouteMapper routeMapper;

  @Autowired
  public RouteServiceImpl(RouteRepository routeRepository,
                          RouteMapper routeMapper) {
    this.routeRepository = routeRepository;
    this.routeMapper = routeMapper;
  }

  @Override
  public RouteDto save(RouteDto routeDto) {
    Route route = routeMapper.toEntity(routeDto);
    return routeMapper.toDto(routeRepository.save(route));
  }

  @Override
  public RouteDto update(RouteDto routeDto) {
    Route route = routeRepository.findById(routeDto.getId())
        .orElseThrow(() -> new ResourceNotFoundException(routeDto.getId()));
    route.setColor(routeDto.getColor());
    route.setCount(routeDto.getCount());
    route.setDescription(routeDto.getDescription());
    return routeMapper.toDto(routeRepository.save(route));
  }

  @Override
  public void delete(Long id) {
    Route route = routeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    routeRepository.delete(route);
  }

  @Override
  public List<RouteDto> findAll(RouteSearchCriteriaDto criteria) {
    Specification<Route> routeSpecification = buildSpecification(criteria);
    return routeMapper.toDtoList(routeRepository.findAll(routeSpecification));
  }

  private Specification<Route> buildSpecification(RouteSearchCriteriaDto criteriaDto) {
    Specification<Route> specification =
        Specification.where(findByCreated(criteriaDto.getCreated()));
    if (criteriaDto.getUserId() != null) {
      specification = specification.and(findByUserId(criteriaDto.getUserId()));
    }
    if (criteriaDto.getColor() != null) {
      specification = specification.and(findByColor(criteriaDto.getColor()));
    }
    return specification;
  }

  @Override
  public RouteDto findById(Long id) {
    Route route = routeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    return routeMapper.toDto(route);
  }
}
