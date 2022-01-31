package com.telegram.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.telegram.model.BotState;
import com.telegram.service.validator.NullableNotBlank;
import org.hibernate.validator.constraints.Length;
import org.telegram.telegrambots.meta.api.objects.User;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TelegramUserDto {
  @PositiveOrZero(message = "Id can`t be smaller than 0")
  private Long id;
  @NotBlank(message = "First name can`t be null and spaces!")
  @Length(min = 2, max = 20,
          message = "First name should be less than 20 and bigger than 2 characters")
  private String firstName;
  @NullableNotBlank(message = "Last name can`t be spaces!")
  @Length(min = 2, max = 20,
          message = "Last name should be less than 20 and bigger than 2 characters")
  private String lastName;
  @NullableNotBlank(message = "Username can`t be spaces!")
  @Length(min = 2, max = 20,
          message = "Username should be less than 20 and bigger than 2 characters")
  private String userName;
  @NotNull(message = "Chat id can`t be null")
  @PositiveOrZero(message = "Chat id can`t be smaller than 0")
  private Long chatId;
  private BotState botState = BotState.START;
  @Valid
  private Set<RouteDto> routeDtos;

  public TelegramUserDto() {
  }

  public TelegramUserDto(User user, Long chatId) {
    this.userName = user.getUserName();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.chatId = chatId;
  }

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

  public BotState getBotState() {
    return botState;
  }

  public void setBotState(BotState botState) {
    this.botState = botState;
  }
}
