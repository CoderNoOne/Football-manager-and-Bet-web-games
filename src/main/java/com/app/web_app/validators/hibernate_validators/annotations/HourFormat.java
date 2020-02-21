package com.app.web_app.validators.hibernate_validators.annotations;

import com.app.web_app.validators.hibernate_validators.impl.HourValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HourValidatorImpl.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HourFormat {

    String message() default "hour format should be of HH:mm";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
