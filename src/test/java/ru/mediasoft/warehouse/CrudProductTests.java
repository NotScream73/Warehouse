package ru.mediasoft.warehouse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import ru.mediasoft.warehouse.product.model.dto.SaveProductDto;
import ru.mediasoft.warehouse.product.model.dto.ViewProductDto;
import ru.mediasoft.warehouse.product.service.ProductService;
import ru.mediasoft.warehouse.product.service.exception.ProductArticleAlreadyExistsException;
import ru.mediasoft.warehouse.product.service.exception.ProductNotFoundException;

import java.util.UUID;

@SpringBootTest
public class CrudProductTests {
    @Autowired
    private ProductService productService;
    @Test
    void testProductCreate() {

        productService.deleteAll();

        final SaveProductDto saveProductDto = new SaveProductDto("name", "article", "description", "category", 0.0, 0);

        Assertions.assertNotNull(productService.create(saveProductDto).getId());
    }

    @Test
    void testProductRead() {

        productService.deleteAll();

        final SaveProductDto saveProductDto = new SaveProductDto("name", "article", "description", "category", 0.0, 0);

        final ViewProductDto viewProductDto = productService.create(saveProductDto);

        final ViewProductDto findViewProductDto = productService.findById(viewProductDto.getId());

        Assertions.assertEquals(viewProductDto, findViewProductDto);
    }

    @Test
    void testProductReadNotFound() {

        productService.deleteAll();

        Assertions.assertThrows(ProductNotFoundException.class, () -> productService.findById(UUID.randomUUID()));
    }

    @Test
    void testProductReadArticleAlreadyExists() {

        productService.deleteAll();

        final SaveProductDto saveProductDto1 = new SaveProductDto("name", "article", "description", "category", 0.0, 0);
        final SaveProductDto saveProductDto2 = new SaveProductDto("name", "article", "description", "category", 0.0, 0);

        productService.create(saveProductDto1);

        Assertions.assertThrows(ProductArticleAlreadyExistsException.class, () -> productService.create(saveProductDto2));
    }

    @Test
    void testProductReadAll() {
        productService.deleteAll();

        final SaveProductDto saveProductDto1 = new SaveProductDto("name1", "article1", "description1", "category1", 1.0, 1);
        final SaveProductDto saveProductDto2 = new SaveProductDto("name2", "article2", "description2", "category2", 2.0, 2);

        productService.create(saveProductDto1);
        productService.create(saveProductDto2);

        final Page<ViewProductDto> list = productService.findAll(0,10);

        Assertions.assertEquals(list.getTotalElements(), 2);
    }

    @Test
    void testProductReadAllEmpty() {

        productService.deleteAll();

        final Page<ViewProductDto> products = productService.findAll(0, 10);

        Assertions.assertEquals(products.getTotalElements(), 0);
    }
}
