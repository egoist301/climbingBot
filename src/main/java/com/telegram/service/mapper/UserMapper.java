package com.telegram.service.mapper;

import com.telegram.controller.dto.UserDto;
import com.telegram.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserDto> {
  @Autowired
  protected UserMapper(ModelMapper mapper) {
    super(User.class, UserDto.class, mapper);
  }
}
