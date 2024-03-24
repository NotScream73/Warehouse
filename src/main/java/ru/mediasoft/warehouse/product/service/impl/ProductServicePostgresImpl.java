package ru.mediasoft.warehouse.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mediasoft.warehouse.product.model.Product;
import ru.mediasoft.warehouse.product.model.dto.SaveProductDto;
import ru.mediasoft.warehouse.product.model.dto.ViewProductDto;
import ru.mediasoft.warehouse.product.repository.ProductRepository;
import ru.mediasoft.warehouse.product.service.exception.ProductArticleAlreadyExistsException;
import ru.mediasoft.warehouse.product.service.exception.ProductNotFoundException;
import ru.mediasoft.warehouse.product.service.ProductService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Реализация сервиса продукта в PostgreSQL.
 */
@Service
@RequiredArgsConstructor
public class ProductServicePostgresImpl implements ProductService {
    private final ProductRepository productRepository;

    /**
     * Получить все продукты.
     *
     * @param page Номер страницы
     * @param size Размер страницы
     * @return Пагинированный список продуктов в ViewProductDTO
     */
    @Transactional(readOnly = true)
    @Override
    public Page<ViewProductDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable).map(ViewProductDto::new);
    }

    /**
     * Получить продукт по ID.
     *
     * @param id Уникальный идентификатор продукта.
     * @return Продукт в ViewProductDTO.
     */
    @Transactional(readOnly = true)
    @Override
    public ViewProductDto findById(UUID id) {
        return new ViewProductDto(productRepository.findById(id)
                                                   .orElseThrow(() -> new ProductNotFoundException(id)));
    }

    /**
     * Создать новый продукт.
     *
     * @param saveProductDto Данные продукта.
     * @return Созданный продукт.
     */
    @Transactional
    @Override
    public ViewProductDto create(SaveProductDto saveProductDto) {
        if (productRepository.existsByArticle(saveProductDto.getArticle())) {
            throw new ProductArticleAlreadyExistsException(saveProductDto.getArticle());
        }
        Product product = new Product(saveProductDto)
                                    .setCreatedAt(new Timestamp(new Date().getTime()))
                                    .setUpdatedAt(new Timestamp(new Date().getTime()));
        return new ViewProductDto(productRepository.save(product));
    }

    /**
     * Обновить продукт.
     *
     * @param id ID продукта для обновления.
     * @param saveProductDto Обновленные данные продукта.
     * @return Обновленный продукт.
     */
    @Transactional
    @Override
    public ViewProductDto update(UUID id, SaveProductDto saveProductDto) {
        Product currentProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        if (productRepository.existsByArticle(saveProductDto.getArticle())
                && !Objects.equals(saveProductDto.getArticle(), currentProduct.getArticle())) {
            throw new ProductArticleAlreadyExistsException(saveProductDto.getArticle());
        }
        currentProduct
                .setName(saveProductDto.getName())
                .setArticle(saveProductDto.getArticle())
                .setDescription(saveProductDto.getDescription())
                .setCategory(saveProductDto.getCategory())
                .setPrice(saveProductDto.getPrice());
        if (!Objects.equals(currentProduct.getQuantity(), saveProductDto.getQuantity())) {
            currentProduct
                    .setQuantity(saveProductDto.getQuantity())
                    .setUpdatedAt(new Timestamp(new Date().getTime()));
        }
        return new ViewProductDto(productRepository.save(currentProduct));
    }

    /**
     * Удалить продукт по ID.
     *
     * @param id ID продукта для удаления.
     */
    @Transactional
    @Override
    public void delete(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }

    /**
     * Удалить все продукты.
     */
    @Transactional
    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }
}
