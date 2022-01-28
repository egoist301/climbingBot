package com.telegram.service.mapper;

import com.telegram.controller.dto.UserDto;
import com.telegram.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserDto> {
  @Autowired
  protected UserMapper(Class<User> entityClass, Class<UserDto> dtoClass, ModelMapper mapper) {
    super(entityClass, dtoClass, mapper);
  }
}
