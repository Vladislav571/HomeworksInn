package main.java.ru.orel.java.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.java.ru.orel.java.dto.PizzaDto;
import main.java.ru.orel.java.service.PizzaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
@RequiredArgsConstructor

@Tag(name = "Пиццы", description = "API для управления пиццами: получение, создание, обновление, удаление и поиск")
public class PizzaController {

    private final PizzaService pizzaService;
    private static final Logger log = LoggerFactory.getLogger(PizzaController.class);
    /**
     * GET /api/pizzas - Получить все пиццы
     */

    @Operation(
            summary = "Получить все активные пиццы",
            description = "Возвращает список всех пицц, которые не помечены как удалённые (is_deleted = false)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно получен список пицц"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })

    @GetMapping
    public ResponseEntity<List<PizzaDto>> getAllPizzas() {
        log.info("GET /api/pizzas - Получение всех пицц");
        List<PizzaDto> pizzas = pizzaService.getAllPizzas();
        return ResponseEntity.ok(pizzas);
    }

    /**
     * GET /api/pizzas/{id} - Получить пиццу по ID
     */

    @Operation(
            summary = "Получить пиццу по ID",
            description = "Возвращает пиццу по указанному ID, если она существует и не удалена"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пицца найдена и возвращена"),
            @ApiResponse(responseCode = "404", description = "Пицца с указанным ID не найдена"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })

    @GetMapping("/{id}")
    public ResponseEntity<PizzaDto> getPizzaById(@PathVariable Long id) {
        log.info("GET /api/pizzas/{} - Получение пиццы по ID", id);
        try {
            PizzaDto pizza = pizzaService.getPizzaById(id);
            return ResponseEntity.ok(pizza);
        } catch (RuntimeException e) {
            log.error("Пицца с ID {} не найдена", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET /api/pizzas/search?name=... - Поиск пицц по названию
     */

    @Operation(
            summary = "Поиск пицц по названию",
            description = "Ищет пиццы, в названии которых содержится указанная подстрока (без учёта регистра)"
    )
    @Parameters({
            @Parameter(
                    name = "name",
                    description = "Подстрока для поиска в названии пиццы",
                    required = true,
                    example = "пепперони"
            )
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно найден список пицц"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос (например, пустой параметр name)"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })

    @GetMapping("/search")
    public ResponseEntity<List<PizzaDto>> searchPizzas(@RequestParam String name) {
        log.info("GET /api/pizzas/search?name={} - Поиск пицц", name);
        List<PizzaDto> pizzas = pizzaService.searchPizzasByName(name);
        return ResponseEntity.ok(pizzas);
    }

    @Operation(
            summary = "Создать новую пиццу",
            description = "Добавляет новую пиццу в базу данных"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Пицца успешно создана"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные в теле запроса"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })

    /**
     * POST /api/pizzas - Создать новую пиццу
     */
    @PostMapping
    public ResponseEntity<PizzaDto> createPizza(@RequestBody PizzaDto pizzaDto) {
        log.info("POST /api/pizzas - Создание новой пиццы: {}", pizzaDto.getName());
        PizzaDto created = pizzaService.createPizza(pizzaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(
            summary = "Обновить пиццу",
            description = "Обновляет данные существующей пиццы по указанному ID"
    )
    @Parameters({
            @Parameter(
                    name = "id",
                    description = "ID пиццы для обновления",
                    required = true,
                    example = "1"
            )
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пицца успешно обновлена"),
            @ApiResponse(responseCode = "404", description = "Пицца с указанным ID не найдена"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные в теле запроса"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })

    /**
     * PUT /api/pizzas/{id} - Обновить пиццу
     */
    @PutMapping("/{id}")
    public ResponseEntity<PizzaDto> updatePizza(@PathVariable Long id, @RequestBody PizzaDto pizzaDto) {
        log.info("PUT /api/pizzas/{} - Обновление пиццы", id);
        try {
            PizzaDto updated = pizzaService.updatePizza(id, pizzaDto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            log.error("Пицца с ID {} не найдена", id);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Удалить пиццу (Soft Delete)",
            description = "Помечает пиццу как удалённую (is_deleted = true), не удаляя запись физически"
    )
    @Parameters({
            @Parameter(
                    name = "id",
                    description = "ID пиццы для удаления",
                    required = true,
                    example = "1"
            )
    })
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Пицца успешно помечена как удалённая"),
            @ApiResponse(responseCode = "404", description = "Пицца с указанным ID не найдена"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })

    /**
     * DELETE /api/pizzas/{id} - Удалить пиццу (Soft Delete)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePizza(@PathVariable Long id) {
        log.info("DELETE /api/pizzas/{} - Удаление пиццы", id);
        try {
            pizzaService.deletePizza(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            log.error("Пицца с ID {} не найдена", id);
            return ResponseEntity.notFound().build();
        }
    }
}

