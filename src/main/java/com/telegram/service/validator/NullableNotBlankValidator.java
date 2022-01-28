package com.telegram.service.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class NullableNotBlankValidator implements
    ConstraintValidator<NullableNotBlank, CharSequence> {
  @Override
  public boolean isValid(CharSequence charSequence,
                         ConstraintValidatorContext constraintValidatorContext) {
    return Objects.isNull(charSequence) || charSequence.toString().trim().length() > 0;
  }
}
