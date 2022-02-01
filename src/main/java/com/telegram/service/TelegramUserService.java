package com.telegram.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.telegram.controller.dto.TelegramUserDto;

@Service
public interface TelegramUserService {
  TelegramUserDto save(TelegramUserDto userDto);

  TelegramUserDto update(TelegramUserDto userDto);

  void delete(Long id);

  Set<TelegramUserDto> findAll();

  TelegramUserDto findById(Long id);

  TelegramUserDto findByChatId(Long chatId);
}
