package ru.mediasoft.warehouse.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Класс конфигурации для настройки Swagger.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Бин конфигурации Swagger.
     *
     * @return Объект OpenAPI с настройками сервера и информации об API.
     */
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .servers(
                        List.of(
                                new Server().url("http://localhost:8080")
                        )
                )
                .info(
                        new Info().title("Warehouse API")
                );
    }
}
