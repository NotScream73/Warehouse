package ru.mediasoft.warehouse.product.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.mediasoft.warehouse.product.model.Product;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * DTO для представления информации о продукте.
 */
@Data
@Accessors(chain = true)
@Schema(description = "DTO для представления информации о продукте")
public class ViewProductDto {

    /**
     * Уникальный идентификатор продукта.
     */
    @Schema(description = "Уникальный идентификатор продукта")
    private UUID id;

    /**
     * Название продукта.
     */
    @Schema(description = "Название продукта")
    private String name;

    /**
     * Уникальный артикул продукта.
     */
    @Schema(description = "Уникальный артикул продукта")
    private String article;

    /**
     * Описание продукта.
     */
    @Schema(description = "Описание продукта")
    private String description;

    /**
     * Категория продукта.
     */
    @Schema(description = "Категория продукта")
    private String category;

    /**
     * Цена продукта.
     */
    @Schema(description = "Цена продукта")
    private Double price;

    /**
     * Количество продукта на складе.
     */
    @Schema(description = "Количество продукта на складе")
    private Integer quantity;

    /**
     * Дата создания продукта.
     */
    @Schema(description = "Дата создания продукта")
    private Timestamp createdAt;

    /**
     * Дата последнего обновления количества ппродукта.
     */
    @Schema(description = "Дата последнего обновления количества ппродукта")
    private Timestamp updatedAt;

    /**
     * Конструктор DTO представления продукта из продукта.
     *
     * @param product продукт.
     */
    public ViewProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.article = product.getArticle();
        this.description = product.getDescription();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();
    }
}
