package com.careersforyou.jobservice.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JobValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        var job =
                new Job("1", "Junior Java Developer", "Entry-Level Java Developer", "ACC", "Java", "Spring Boot");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenJobidDefinedButIncorrectThenValidationFails() {
        var job =
                new Job("invalid", "Junior Java Developer", "Entry-Level Java Developer", "ACC", "Java", "Spring Boot");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
//        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).contains("The jobid format must be valid.");
    }
}