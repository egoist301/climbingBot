package com.telegram.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.telegram.model.Color;

import java.time.LocalDate;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteSearchCriteriaDto {
  private Color color;
  private LocalDate created;
  private Long userId;
}
