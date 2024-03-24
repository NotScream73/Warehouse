package ru.mediasoft.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Класс с точкой входа
 */
@SpringBootApplication
public class WarehouseApplication {

    /**
     * Точка входа
     * @param args параметры входа
     */
    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }
}
