package com.telegram.controller.dto;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TelegramUserDtoTest {
  private static Validator validator;

  @BeforeClass
  public static void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void negativeId() {
    // Given
    TelegramUserDto userDto = new TelegramUserDto();
    userDto.setId(-1L);
    userDto.setFirstName("John");
    userDto.setChatId(1L);

    // When
    Set<ConstraintViolation<TelegramUserDto>> constraintViolations = validator.validate(userDto);

    // Then
    assertEquals(1, constraintViolations.size());
    assertEquals("Id can`t be smaller than 0", constraintViolations.iterator().next().getMessage());
  }

  @Test
  public void negativeChatId() {
    // Given
    TelegramUserDto userDto = new TelegramUserDto();
    userDto.setFirstName("John");
    userDto.setChatId(-1L);

    // When
    Set<ConstraintViolation<TelegramUserDto>> constraintViolations =
        validator.validate(userDto);

    // Then
    assertEquals(1, constraintViolations.size());
    assertEquals("Chat id can`t be smaller than 0",
        constraintViolations.iterator().next().getMessage());
  }

  @Test
  public void chatIdIsNull() {
    // Given
    TelegramUserDto userDto = new TelegramUserDto();
    userDto.setFirstName("John");

    // When
    Set<ConstraintViolation<TelegramUserDto>> constraintViolations =
        validator.validate(userDto);

    // Then
    assertEquals(1, constraintViolations.size());
    assertEquals("Chat id can`t be null",
        constraintViolations.iterator().next().getMessage());
  }

  @Test
  public void largeUsername() {
    // Given
    TelegramUserDto userDto = new TelegramUserDto();
    userDto.setFirstName("John");
    userDto.setLastName("Wick");
    userDto.setUserName("chapterchapterchapter");
    userDto.setChatId(1L);

    // When
    Set<ConstraintViolation<TelegramUserDto>> constraintViolations =
        validator.validate(userDto);

    // Then
    assertEquals(1, constraintViolations.size());
    assertEquals("Username should be less than 20 and bigger than 2 characters",
        constraintViolations.iterator().next().getMessage());
  }

  @Test
  public void smallUsername() {
    // Given
    TelegramUserDto userDto = new TelegramUserDto();
    userDto.setFirstName("John");
    userDto.setLastName("Wick");
    userDto.setUserName("c");
    userDto.setChatId(1L);

    // When
    Set<ConstraintViolation<TelegramUserDto>> constraintViolations =
        validator.validate(userDto);

    // Then
    assertEquals(1, constraintViolations.size());
    assertEquals("Username should be less than 20 and bigger than 2 characters",
        constraintViolations.iterator().next().getMessage());
  }

  @Test
  public void usernameIsBlank() {
    // Given
    TelegramUserDto userDto = new TelegramUserDto();
    userDto.setFirstName("John");
    userDto.setLastName("Wick");
    userDto.setUserName("        ");
    userDto.setChatId(1L);

    // When
    Set<ConstraintViolation<TelegramUserDto>> constraintViolations =
        validator.validate(userDto);

    // Then
    assertEquals(1, constraintViolations.size());
    assertEquals("Username can`t be spaces!",
        constraintViolations.iterator().next().getMessage());
  }
}