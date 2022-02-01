package com.telegram.service.mapper;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.model.TelegramUser;

@Component
public class TelegramUserMapper implements Mapper<TelegramUser, TelegramUserDto> {
  @Override
  public TelegramUser toEntity(TelegramUserDto userDto) {
    if (userDto == null) {
      return null;
    }
    TelegramUser user = new TelegramUser();
    user.setBotState(userDto.getBotState());
    user.setUserName(userDto.getUserName());
    user.setFirstName(userDto.getFirstName());
    user.setChatId(userDto.getChatId());
    user.setLastName(userDto.getLastName());
    user.setId(userDto.getId());
    return user;
  }

  @Override
  public TelegramUserDto toDto(TelegramUser telegramUser) {
    if (telegramUser == null) {
      return null;
    }
    TelegramUserDto userDto = new TelegramUserDto();
    userDto.setBotState(telegramUser.getBotState());
    userDto.setUserName(telegramUser.getUserName());
    userDto.setFirstName(telegramUser.getFirstName());
    userDto.setChatId(telegramUser.getChatId());
    userDto.setLastName(telegramUser.getLastName());
    userDto.setId(telegramUser.getId());
    return userDto;
  }

  @Override
  public Set<TelegramUserDto> toDtoSet(Collection<TelegramUser> telegramUsers) {
    return telegramUsers.stream().map(this::toDto).collect(Collectors.toSet());
  }

  @Override
  public Set<TelegramUser> toEntitySet(Collection<TelegramUserDto> dList) {
    return dList.stream().map(this::toEntity).collect(Collectors.toSet());
  }
}
