package com.telegram.service.impl;

import com.telegram.controller.dto.UserDto;
import com.telegram.model.User;
import com.telegram.repository.UserRepository;
import com.telegram.service.UserService;
import com.telegram.service.exception.ResourceNotFoundException;
import com.telegram.service.mapper.RouteMapper;
import com.telegram.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final RouteMapper routeMapper;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
                         RouteMapper routeMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.routeMapper = routeMapper;
  }

  @Override
  public UserDto save(UserDto userDto) {
    User user = userMapper.toEntity(userDto);
    return userMapper.toDto(userRepository.save(user));
  }

  @Override
  public UserDto update(UserDto userDto) {
    User user = userRepository.findById(userDto.getId())
        .orElseThrow(() -> new ResourceNotFoundException(userDto.getId()));
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setRoutes(routeMapper.toEntitySet(userDto.getRouteDtos()));
    return userMapper.toDto(userRepository.save(user));
  }

  @Override
  public void delete(Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    userRepository.delete(user);
  }

  @Override
  public List<UserDto> findAll() {
    return userMapper.toDtoList(userRepository.findAll());
  }

  @Override
  public UserDto findById(Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    return userMapper.toDto(user);
  }
}
