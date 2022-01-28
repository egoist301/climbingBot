package com.telegram.controller;

import com.telegram.controller.dto.RouteDto;
import com.telegram.controller.dto.RouteSearchCriteriaDto;
import com.telegram.model.Color;
import com.telegram.service.RouteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
public class RouteController {
  private final RouteService routeService;

  public RouteController(RouteService routeService) {
    this.routeService = routeService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<RouteDto> get(@PathVariable(name = "id") Long id) {
    return new ResponseEntity<>(routeService.findById(id), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<RouteDto>> getAll(@RequestParam(name = "created", required = false)
                                               @DateTimeFormat(pattern = "dd.MM.yyyy")
                                                   LocalDate created,
                                               @RequestParam(name = "color", required = false)
                                                   Color color,
                                               @RequestParam(name = "userId", required = false)
                                                   Long userId) {
    RouteSearchCriteriaDto criteria = buildSearchCriteria(created, color, userId);
    return new ResponseEntity<>(routeService.findAll(criteria), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<RouteDto> save(@RequestBody @Valid RouteDto routeDto) {
    RouteDto savedRoute = routeService.save(routeDto);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                .buildAndExpand(savedRoute.getId()).toUri());
    return new ResponseEntity<>(savedRoute, httpHeaders, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<RouteDto> update(@RequestBody @Valid RouteDto routeDto,
                                         @PathVariable(name = "id") Long id) {

    routeDto.setId(id);
    return new ResponseEntity<>(routeService.update(routeDto), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable(name = "id") Long id) {
    routeService.delete(id);
  }

  private RouteSearchCriteriaDto buildSearchCriteria(LocalDate created,
                                                     Color color, Long userId) {

    RouteSearchCriteriaDto criteriaDto = new RouteSearchCriteriaDto();
    criteriaDto.setColor(color);
    criteriaDto.setCreated(created);
    criteriaDto.setUserId(userId);
    return criteriaDto;
  }
}
