package ru.mediasoft.warehouse.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mediasoft.warehouse.product.model.dto.SaveProductDto;
import ru.mediasoft.warehouse.product.model.dto.ViewProductDto;
import ru.mediasoft.warehouse.product.service.ProductService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * Контроллер продуктов.
 */
@Tag(name = "Контроллер продуктов")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    /**
     * Сервис продукта.
     */
    private final ProductService productService;

    /**
     * Получить все продукты.
     *
     * @param page Номер страницы
     * @param size Размер страницы
     * @return Список всех продуктов.
     */
    @Operation(
            summary = "Получить все продукты",
            description = "Возвращает список всех продуктов и информацию о пагинации.")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProducts(
            @Parameter(description = "Номер страницы")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Размер страницы")
            @RequestParam(defaultValue = "10") int size) {

        Page<ViewProductDto> products = productService.findAll(page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("products", products.getContent());
        response.put("currentPage", products.getNumber());
        response.put("totalItems", products.getTotalElements());
        response.put("totalPages", products.getTotalPages());

        return new ResponseEntity<>(response, OK);
    }

    /**
     * Получить продукт по ID.
     *
     * @param id ID продукта.
     * @return Продукт по указанному ID.
     */
    @Operation(
            summary = "Получить продукт по ID",
            description = "Возвращает продукт по указанному ID.")
    @GetMapping("{id}")
    public ResponseEntity<ViewProductDto> getProductById(
            @Parameter(description = "ID продукта", required = true)
            @PathVariable UUID id) {

        return new ResponseEntity<>(productService.findById(id), OK);
    }

    /**
     * Создать новый продукт.
     *
     * @param product Данные продукта.
     * @return Созданный продукт.
     */
    @Operation(
            summary = "Создать новый продукт",
            description = "Создаёт новый продукт на основе входных данных.")
    @PostMapping
    public ResponseEntity<ViewProductDto> createProduct(
            @Parameter(description = "Данные продукта", required = true)
            @Valid @RequestBody SaveProductDto product) {

        return new ResponseEntity<>(productService.create(product), CREATED);
    }

    /**
     * Обновить продукт.
     *
     * @param id      ID продукта для обновления.
     * @param product Обновленные данные продукта.
     * @return Обновленный продукт.
     */
    @Operation(
            summary = "Обновить продукт",
            description = "Обновляет существующий продукт на основе входных данными."
    )
    @PutMapping("{id}")
    public ResponseEntity<ViewProductDto> updateProduct(
            @Parameter(description = "ID продукта для обновления", required = true)
            @PathVariable UUID id,
            @Parameter(description = "Обновленные данные продукта", required = true)
            @Valid @RequestBody SaveProductDto product) {

        return new ResponseEntity<>(productService.update(id, product), OK);
    }

    /**
     * Удалить продукт по ID.
     *
     * @param id ID продукта для удаления.
     * @return Сообщение об успешном удалении.
     */
    @Operation(
            summary = "Удалить продукт по ID",
            description = "Удаляет продукт по указанному ID.")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(
            @Parameter(description = "ID продукта для удаления", required = true)
            @PathVariable UUID id) {

        productService.delete(id);

        return new ResponseEntity<>(String.format("Product with id [%s] successfully deleted", id), OK);
    }
}
