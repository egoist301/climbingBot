package com.telegram.service;

import com.telegram.controller.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
  UserDto save(UserDto userDto);

  UserDto update(UserDto userDto);

  void delete(Long id);

  List<UserDto> findAll();

  UserDto findById(Long id);
}
