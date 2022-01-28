package com.telegram.controller.dto;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class RouteDtoTest {
  private static Validator validator;

  @BeforeClass
  public static void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void negativeCount() {
    // Given
    RouteDto routeDto = new RouteDto();
    routeDto.setCount(-1);

    // When
    Set<ConstraintViolation<RouteDto>> constraintViolations =
        validator.validate(routeDto);

    // Then
    assertEquals(1, constraintViolations.size());
    assertEquals("Count can`t be smaller than 0",
                 constraintViolations.iterator().next().getMessage());
  }

  @Test
  public void countIsNull() {
    // Given
    RouteDto routeDto = new RouteDto();

    // When
    Set<ConstraintViolation<RouteDto>> constraintViolations =
        validator.validate(routeDto);

    // Then
    assertEquals(1, constraintViolations.size());
    assertEquals("Count can`t be null",
                 constraintViolations.iterator().next().getMessage());
  }

  @Test
  public void negativeId() {
    // Given
    RouteDto routeDto = new RouteDto();
    routeDto.setId(-1L);
    routeDto.setCount(1);

    // When
    Set<ConstraintViolation<RouteDto>> constraintViolations =
        validator.validate(routeDto);

    // Then
    assertEquals(1, constraintViolations.size());
    assertEquals("Id can`t be smaller than 0",
                 constraintViolations.iterator().next().getMessage());
  }
}