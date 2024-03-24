package ru.mediasoft.warehouse.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mediasoft.warehouse.product.model.Product;

import java.util.UUID;

/**
 * Репозиторий для работы с данными о продуктах.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    /**
     * Проверка существования продукта по артикулу.
     *
     * @param article Артикул продукта.
     * @return True, если продукт с указанным артикулом существует, иначе false.
     */
    boolean existsByArticle(String article);

    /**
     * Получение всех продуктов в соответствии с пагинацией.
     *
     * @param pageable информация о пагинации
     * @return Пагинированный список продуктов
     */
    Page<Product> findAll(Pageable pageable);

}
