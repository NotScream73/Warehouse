package ru.mediasoft.warehouse.util.error;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ru.mediasoft.warehouse.product.service.exception.ProductArticleAlreadyExistsException;
import ru.mediasoft.warehouse.product.service.exception.ProductNotFoundException;
import ru.mediasoft.warehouse.util.validation.ValidationException;

import java.util.stream.Collectors;

/**
 * Класс для глобальной обработки исключений в контроллере.
 */
@ControllerAdvice(annotations = RestController.class)
public class AdviceController {

    /**
     * Обработка исключений
     * ProductNotFoundException,
     * ProductArticleAlreadyExistsException,
     * ValidationException.
     *
     * @param e Исключение.
     * @return Ответ с кодом 400 и сообщением об ошибке.
     */
    @ExceptionHandler({
            ProductNotFoundException.class,
            ProductArticleAlreadyExistsException.class,
            ValidationException.class
    })
    public ResponseEntity<Object> handleException(Throwable e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Обработка исключений MethodArgumentNotValidException, преобразуя его в ValidationException.
     *
     * @param e Исключение.
     * @return Ответ с кодом 400 и сообщением об ошибке.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleBindException(MethodArgumentNotValidException e) {
        final ValidationException validationException = new ValidationException(
                e.getBindingResult().getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toSet()));
        return handleException(validationException);
    }

    /**
     * Обработка исключений Exception.
     *
     * @param e Исключение.
     * @return Ответ с кодом 500 и сообщением об ошибке.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownException(Throwable e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
