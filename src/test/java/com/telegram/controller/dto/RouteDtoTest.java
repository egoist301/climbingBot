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
public class RouteDtoTest {
  private static Validator validator;

  @BeforeClass
  public static void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void negativeId() {
    // Given
    RouteDto routeDto = new RouteDto();
    routeDto.setId(-1L);

    // When
    Set<ConstraintViolation<RouteDto>> constraintViolations =
        validator.validate(routeDto);

    // Then
    assertEquals(1, constraintViolations.size());
    assertEquals("Id can`t be smaller than 0",
        constraintViolations.iterator().next().getMessage());
  }
}