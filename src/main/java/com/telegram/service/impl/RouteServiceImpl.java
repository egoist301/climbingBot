package com.telegram.service.impl;

import static com.telegram.repository.specification.RouteSpecification.exists;
import static com.telegram.repository.specification.RouteSpecification.findByColor;
import static com.telegram.repository.specification.RouteSpecification.findByCreated;
import static com.telegram.repository.specification.RouteSpecification.findByUserId;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.telegram.controller.dto.RouteDto;
import com.telegram.controller.dto.RouteSearchCriteriaDto;
import com.telegram.model.Route;
import com.telegram.repository.RouteRepository;
import com.telegram.repository.TelegramUserRepository;
import com.telegram.service.RouteService;
import com.telegram.service.exception.ResourceNotFoundException;
import com.telegram.service.mapper.RouteMapper;

@Service
public class RouteServiceImpl implements RouteService {
  private final RouteRepository routeRepository;
  private final TelegramUserRepository userRepository;
  private final RouteMapper routeMapper;

  @Autowired
  public RouteServiceImpl(RouteRepository routeRepository,
                          TelegramUserRepository userRepository, RouteMapper routeMapper) {
    this.routeRepository = routeRepository;
    this.userRepository = userRepository;
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
    route.setAttempt(routeDto.getAttempt());
    return routeMapper.toDto(routeRepository.save(route));
  }

  @Override
  public void delete(Long id) {
    Route route = routeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    routeRepository.delete(route);
  }

  @Override
  public Set<RouteDto> findAll(RouteSearchCriteriaDto criteria) {
    Specification<Route> routeSpecification = buildSpecification(criteria);
    return routeMapper.toDtoSet(routeRepository.findAll(routeSpecification));
  }

  private Specification<Route> buildSpecification(RouteSearchCriteriaDto criteriaDto) {
    Specification<Route> specification =
        Specification.where(exists());
    if (criteriaDto.getCreated() != null) {
      specification = specification.and(findByCreated(criteriaDto.getCreated()));
    }
    if (criteriaDto.getUserId() != null) {
      specification = specification.and(findByUserId(userRepository.findById(criteriaDto.getUserId()).get()));
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
