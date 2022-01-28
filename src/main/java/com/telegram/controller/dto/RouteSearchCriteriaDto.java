package com.telegram.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.telegram.model.Color;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteSearchCriteriaDto {
  private Color color;
  private LocalDate created;
  private Long userId;

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public LocalDate getCreated() {
    return created;
  }

  public void setCreated(LocalDate created) {
    this.created = created;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}
