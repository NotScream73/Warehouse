package ru.mediasoft.warehouse.product.service.exception;

/**
 * Исключение для продукта с артикулом, который уже существует.
 */
public class ProductArticleAlreadyExistsException extends RuntimeException {

    /**
     * Конструктор по артикулу.
     *
     * @param article Артикул продукта, который уже существует.
     */
    public ProductArticleAlreadyExistsException(String article) {
        super(String.format("Product with article [%s] is already exists", article));
    }
}
