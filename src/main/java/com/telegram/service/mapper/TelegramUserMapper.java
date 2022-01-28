package com.telegram.service.mapper;

import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.model.TelegramUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelegramUserMapper extends AbstractMapper<TelegramUser, TelegramUserDto> {
  @Autowired
  protected TelegramUserMapper(ModelMapper mapper) {
    super(TelegramUser.class, TelegramUserDto.class, mapper);
  }
}
