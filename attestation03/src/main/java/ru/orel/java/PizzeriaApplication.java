package ru.orel.java;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Pizzeria API",
                description = "API для управления заказами и пиццами",
                version = "1.0.0"
        ),
        externalDocs = @ExternalDocumentation(
                description = "Документация проекта",
                url = "https://example.com/docs"
        )
)
public class PizzeriaApplication {
    public static void main(String[] args) {
        SpringApplication.run(PizzeriaApplication.class, args);
    }
}
