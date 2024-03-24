package ru.mediasoft.warehouse.util.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Утилита для валидации объектов.
 */
@Component
public class ValidatorUtil {

    /**
     * Валидатор, используемый для валидации.
     */
    private final Validator validator;

    /**
     * Конструктор.
     */
    public ValidatorUtil() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * Валидация объекта.
     *
     * @param object Объект, который необходимо валидировать.
     * @param <T> Тип объекта.
     * @throws ValidationException если есть ошибки валидации.
     */
    public <T> void validate(T object) {
        final Set<ConstraintViolation<T>> errors = validator.validate(object);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet()));
        }
    }
}