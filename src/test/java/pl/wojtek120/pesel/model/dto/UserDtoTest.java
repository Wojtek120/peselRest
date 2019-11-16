package pl.wojtek120.pesel.model.dto;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDtoTest {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Test
    public void Should_HaveNoViolations_When_PeselIsValid() {
        //given:
        UserDto user = new UserDto(1L, "First", "Last", "03093069448", "");

        //when:
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);

        //then:
        assertTrue(violations.isEmpty());
    }

    @Test
    public void Should_HaveViolations_When_PeselIsInvalid() {
        //given:
        UserDto user = new UserDto(1L, "First", "Last", "03099969448", "");

        //when:
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);

        //then:
        assertEquals(violations.size(), 1);

        ConstraintViolation<UserDto> violation = violations.iterator().next();

        assertEquals("PESEL number is invalid", violation.getMessage());
        assertEquals("pesel", violation.getPropertyPath().toString());
        assertEquals("03099969448", violation.getInvalidValue());
    }

    @Test
    public void Should_HaveViolations_When_PeselIsToShort() {
        //given:
        UserDto user = new UserDto(1L, "First", "Last", "030930694", "");

        //when:
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);

        //then:
        assertEquals(violations.size(), 1);

        ConstraintViolation<UserDto> violation = violations.iterator().next();

        assertEquals("PESEL number is invalid", violation.getMessage());
        assertEquals("pesel", violation.getPropertyPath().toString());
        assertEquals("030930694", violation.getInvalidValue());
    }

    @Test
    public void Should_HaveViolations_When_PeselIsEmpty() {
        //given:
        UserDto user = new UserDto(1L, "First", "Last", "", "");

        //when:
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);

        //then:
        assertEquals(violations.size(), 1);

        ConstraintViolation<UserDto> violation = violations.iterator().next();

        assertEquals("PESEL number is invalid", violation.getMessage());
        assertEquals("pesel", violation.getPropertyPath().toString());
        assertEquals("", violation.getInvalidValue());
    }
}