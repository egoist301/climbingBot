package com.telegram.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.telegram.model.Color;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteDto {
  @PositiveOrZero(message = "Id can`t be smaller than 0")
  private Long id;
  private Color color = Color.WHITE;
  @Pattern(regexp = ".{0,255}", message = "Description can`t be bigger then 255 symbols")
  private String description;
  @PositiveOrZero(message = "Count can`t be smaller than 0")
  private Integer count;
  private LocalDate created;
  @Valid
  private UserDto userDto;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public LocalDate getCreated() {
    return created;
  }

  public void setCreated(LocalDate created) {
    this.created = created;
  }

  public UserDto getUserDto() {
    return userDto;
  }

  public void setUserDto(UserDto userDto) {
    this.userDto = userDto;
  }
}
