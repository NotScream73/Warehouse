package ru.mediasoft.warehouse.product.service.exception;

import java.util.UUID;

/**
 * Исключение для несуществующего продукта по ID.
 */
public class ProductNotFoundException extends RuntimeException {

    /**
     * Конструктор по ID.
     *
     * @param id Уникальный идентификатор ненайденного продукта.
     */
    public ProductNotFoundException(UUID id) {
        super(String.format("Product with id [%s] is not found", id));
    }
}
