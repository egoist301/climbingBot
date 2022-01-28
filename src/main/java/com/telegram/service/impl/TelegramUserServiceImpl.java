package com.telegram.service.impl;

import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.model.TelegramUser;
import com.telegram.repository.TelegramUserRepository;
import com.telegram.service.TelegramUserService;
import com.telegram.service.exception.ResourceNotFoundException;
import com.telegram.service.mapper.RouteMapper;
import com.telegram.service.mapper.TelegramUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelegramUserServiceImpl implements TelegramUserService {
  private final TelegramUserRepository telegramUserRepository;
  private final TelegramUserMapper telegramUserMapper;
  private final RouteMapper routeMapper;

  @Autowired
  public TelegramUserServiceImpl(TelegramUserRepository telegramUserRepository,
                                 TelegramUserMapper telegramUserMapper,
                                 RouteMapper routeMapper) {
    this.telegramUserRepository = telegramUserRepository;
    this.telegramUserMapper = telegramUserMapper;
    this.routeMapper = routeMapper;
  }

  @Override
  public TelegramUserDto save(TelegramUserDto userDto) {
    TelegramUser user = telegramUserMapper.toEntity(userDto);
    return telegramUserMapper.toDto(telegramUserRepository.save(user));
  }

  @Override
  public TelegramUserDto update(TelegramUserDto userDto) {
    TelegramUser user = telegramUserRepository.findById(userDto.getId())
        .orElseThrow(() -> new ResourceNotFoundException(userDto.getId()));
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setRoutes(routeMapper.toEntitySet(userDto.getRouteDtos()));
    return telegramUserMapper.toDto(telegramUserRepository.save(user));
  }

  @Override
  public void delete(Long id) {
    TelegramUser
        user = telegramUserRepository
        .findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    telegramUserRepository.delete(user);
  }

  @Override
  public List<TelegramUserDto> findAll() {
    return telegramUserMapper.toDtoList(telegramUserRepository.findAll());
  }

  @Override
  public TelegramUserDto findById(Long id) {
    TelegramUser
        user = telegramUserRepository
        .findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    return telegramUserMapper.toDto(user);
  }

  @Override
  public TelegramUserDto findByChatId(Long chatId) {
    TelegramUser user = telegramUserRepository.findByChatId(chatId);
    return telegramUserMapper.toDto(user);
  }
}
