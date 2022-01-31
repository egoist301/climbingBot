package com.telegram.controller.dto;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.telegram.model.Color;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteDto {
  @PositiveOrZero(message = "Id can`t be smaller than 0")
  private Long id;
  private Color color = Color.WHITE;
  @NotBlank(message = "Attempt can`t be null and spaces!")
  @Length(min = 1, max = 20,
      message = "Attempt should be less than 20 and bigger than 1 characters")
  private String attempt;
  private LocalDate created;
  @Valid
  private TelegramUserDto userDto;

  public RouteDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public String getAttempt() {
    return attempt;
  }

  public void setAttempt(String attempt) {
    this.attempt = attempt;
  }

  public LocalDate getCreated() {
    return created;
  }

  public void setCreated(LocalDate created) {
    this.created = created;
  }

  public TelegramUserDto getUserDto() {
    return userDto;
  }

  public void setUserDto(TelegramUserDto userDto) {
    this.userDto = userDto;
  }
}
