package ru.mediasoft.warehouse.product.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * DTO для сохранения информации о продукте.
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "DTO для сохранения информации о продукте")
public class SaveProductDto {

    /**
     * Название продукта.
     */
    @NotBlank(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    @Size(max = 255, message = "Name maximum length is 255 char")
    @Schema(description = "Название продукта")
    private String name;

    /**
     * Уникальный артикул продукта.
     */
    @NotBlank(message = "Article cannot be empty")
    @NotNull(message = "Article cannot be null")
    @Size(max = 255, message = "Article maximum length is 255 char")
    @Schema(description = "Уникальный артикул продукта")
    private String article;

    /**
     * Описание продукта.
     */
    @NotBlank(message = "Description cannot be empty")
    @NotNull(message = "Description cannot be null")
    @Schema(description = "Описание продукта")
    private String description;

    /**
     * Категория продукта.
     */
    @NotBlank(message = "Category cannot be empty")
    @NotNull(message = "Category cannot be null")
    @Size(max = 255, message = "Category maximum length is 255 char")
    @Schema(description = "Категория продукта")
    private String category;

    /**
     * Цена продукта.
     */
    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price cannot be less than 0")
    @Schema(description = "Цена продукта")
    private Double price;

    /**
     * Количество продукта на складе.
     */
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 0, message = "Quantity cannot be less than 0")
    @Schema(description = "Количество продукта на складе")
    private Integer quantity;
}
