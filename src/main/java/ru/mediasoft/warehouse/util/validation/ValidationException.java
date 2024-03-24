package ru.mediasoft.warehouse.util.validation;

import java.util.Set;

/**
 * Исключение при валидации данных.
 */
public class ValidationException extends RuntimeException {

    /**
     * Конструктор со списком сообщений ошибкок.
     *
     * @param errors Строка с ошибками валидации.
     * @param <T> Тип объекта.
     */
    public <T> ValidationException(Set<String> errors) {
        super(String.join("\n", errors));
    }

    /**
     * Конструктор с сообщением об ошибке.
     *
     * @param error Строка с ошибками валидации.
     * @param <T> Тип объекта.
     */
    public <T> ValidationException(String error) {
        super(error);
    }
}
