package ru.mediasoft.warehouse.product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.mediasoft.warehouse.product.model.dto.SaveProductDto;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Сущность продукта склада.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "products")
public class Product {

    /**
     * Уникальный идентификатор продукта.
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id")
    private UUID id;

    /**
     * Название продукта. Не может быть пустым.
     */
    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name can't be empty")
    private String name;

    /**
     * Уникальный артикул продукта. Не может быть пустым.
     */
    @Column(name = "article", unique = true, nullable = false)
    @NotBlank(message = "Article can't be empty")
    private String article;

    /**
     * Описание продукта. Не может быть пустым.
     */
    @Column(name = "description", nullable = false)
    @NotBlank(message = "Description can't be empty")
    private String description;

    /**
     * Категория продукта. Не может быть пустой.
     */
    @Column(name = "category", nullable = false)
    @NotBlank(message = "Category can't be empty")
    private String category;

    /**
     * Цена продукта.
     */
    @Column(name = "price", nullable = false)
    private Double price;

    /**
     * Количество продукта на складе.
     */
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    /**
     * Время создания продукта.
     */
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    /**
     * Время последнего обновления количества продукта.
     */
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    /**
     * Конструктор продукта из DTO сохранения продукта.
     *
     * @param saveProductDto DTO сохранения продукта.
     */
    public Product(SaveProductDto saveProductDto) {
        this.name = saveProductDto.getName();
        this.article = saveProductDto.getArticle();
        this.description = saveProductDto.getDescription();
        this.category = saveProductDto.getCategory();
        this.price = saveProductDto.getPrice();
        this.quantity = saveProductDto.getQuantity();
    }
}
