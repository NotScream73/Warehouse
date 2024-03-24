package ru.mediasoft.warehouse.product.service;

import org.springframework.data.domain.Page;
import ru.mediasoft.warehouse.product.model.dto.SaveProductDto;
import ru.mediasoft.warehouse.product.model.dto.ViewProductDto;

import java.util.UUID;

/**
 * Сервис продукта.
 */
public interface ProductService {

    /**
     * Получить все продукты.
     *
     * @param page Номер страницы
     * @param size Размер страницы
     * @return Пагинированный список продуктов в ViewProductDTO
     */
    Page<ViewProductDto> findAll(int page, int size);

    /**
     * Получить продукт по ID.
     *
     * @param id Уникальный идентификатор продукта.
     * @return Продукт в ViewProductDTO.
     */
    ViewProductDto findById(UUID id);

    /**
     * Создать новый продукт.
     *
     * @param saveProductDto Данные продукта.
     * @return Созданный продукт.
     */
    ViewProductDto create(SaveProductDto saveProductDto);

    /**
     * Обновить продукт.
     *
     * @param id ID продукта для обновления.
     * @param saveProductDto Обновленные данные продукта.
     * @return Обновленный продукт.
     */
    ViewProductDto update(UUID id, SaveProductDto saveProductDto);

    /**
     * Удалить продукт по ID.
     *
     * @param id ID продукта для удаления.
     */
    void delete(UUID id);

    /**
     * Удалить все продукты.
     */
    void deleteAll();
}
