package com.telegram.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
  @PositiveOrZero(message = "Id can`t be smaller than 0")
  private Long id;
  @NotBlank(message = "First name can`t be null and spaces!")
  @Length(min = 2, max = 20,
          message = "First name should be less than 20 and bigger than 2 characters")
  private String firstName;
  @NotBlank(message = "Last name can`t be null and spaces!")
  @Length(min = 2, max = 20,
          message = "Last name should be less than 20 and bigger than 2 characters")
  private String lastName;
  @NotBlank(message = "Username can`t be null and spaces!")
  @Length(min = 2, max = 20,
          message = "Username should be less than 20 and bigger than 2 characters")
  private String userName;
  @PositiveOrZero(message = "Id can`t be smaller than 0")
  private Long chatId;
  @Valid
  private Set<RouteDto> routeDtos;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Long getChatId() {
    return chatId;
  }

  public void setChatId(Long chatId) {
    this.chatId = chatId;
  }

  public Set<RouteDto> getRouteDtos() {
    return routeDtos;
  }

  public void setRouteDtos(Set<RouteDto> routeDtos) {
    this.routeDtos = routeDtos;
  }
}
