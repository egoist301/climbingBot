package com.telegram.service;

import com.telegram.controller.dto.TelegramUserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TelegramUserService {
  TelegramUserDto save(TelegramUserDto userDto);

  TelegramUserDto update(TelegramUserDto userDto);

  void delete(Long id);

  List<TelegramUserDto> findAll();

  TelegramUserDto findById(Long id);

  TelegramUserDto findByChatId(Long chatId);
}
