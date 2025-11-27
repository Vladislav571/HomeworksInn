package main.java.ru.orel.java.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.java.ru.orel.java.dto.CreateOrderRequest;
import main.java.ru.orel.java.dto.OrderDto;
import main.java.ru.orel.java.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor

@Tag(name = "Заказы", description = "API для работы с заказами")
public class OrderController {

    private final OrderService orderService;
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Operation(
            summary = "Получить все заказы",
            description = "Возвращает список всех активных заказов. Можно фильтровать по статусу или телефону."
    )
    @Parameters({
            @Parameter(
                    name = "status",
                    description = "Фильтр по статусу заказа (например, NEW, IN_PROGRESS, COMPLETED)",
                    required = false,
                    example = "NEW"
            ),
            @Parameter(
                    name = "phone",
                    description = "Фильтр по номеру телефона клиента",
                    required = false,
                    example = "+79991234567"
            )
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно получен список заказов"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String phone) {
        log.info("GET /api/orders - Получение заказов (status={}, phone={})", status, phone);

        if (status != null) {
            List<OrderDto> orders = orderService.getOrdersByStatus(status);
            return ResponseEntity.ok(orders);
        }

        if (phone != null) {
            List<OrderDto> orders = orderService.getOrdersByPhone(phone);
            return ResponseEntity.ok(orders);
        }

        List<OrderDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @Operation(
            summary = "Получить заказ по ID",
            description = "Возвращает заказ по указанному ID, если он существует и не удалён"
    )
    @Parameters({
            @Parameter(
                    name = "id",
                    description = "ID заказа",
                    required = true,
                    example = "1"
            )
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Заказ найден и возвращён"),
            @ApiResponse(responseCode = "404", description = "Заказ с указанным ID не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })

    /**
     * GET /api/orders/{id} - Получить заказ по ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        log.info("GET /api/orders/{} - Получение заказа по ID", id);
        try {
            OrderDto order = orderService.getOrderById(id);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            log.error("Заказ с ID {} не найден", id);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Создать новый заказ",
            description = "Добавляет новый заказ в систему. Требуется указать имя и телефон клиента, а также список позиций (пицц)."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Заказ успешно создан"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные в теле запроса (например, отсутствует имя клиента)"),
            @ApiResponse(responseCode = "404", description = "Пицца с указанным ID в позициях не найдена"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })

    /**
     * POST /api/orders - Создать новый заказ
     */
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderRequest request) {
        log.info("POST /api/orders - Создание нового заказа для клиента: {}", request.getCustomerName());
        try {
            OrderDto created = orderService.createOrder(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            log.error("Ошибка при создании заказа: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Обновить статус заказа",
            description = "Меняет статус указанного заказа (например, с NEW на IN_PROGRESS)"
    )
    @Parameters({
            @Parameter(
                    name = "id",
                    description = "ID заказа для обновления статуса",
                    required = true,
                    example = "1"
            )
    })
    @ApiResponses({
            @ApiResponse(responseCode = "2Desktop", description = "Статус заказа успешно обновлён"),
            @ApiResponse(responseCode = "404", description = "Заказ с указанным ID не найден"),
            @ApiResponse(responseCode = "400", description = "Некорректный статус в теле запроса"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })

    /**
     * PATCH /api/orders/{id}/status - Обновить статус заказа
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderDto> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {
        log.info("PATCH /api/orders/{}/status - Обновление статуса заказа", id);
        try {
            String status = request.get("status");
            OrderDto updated = orderService.updateOrderStatus(id, status);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            log.error("Заказ с ID {} не найден", id);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Удалить заказ (Soft Delete)",
            description = "Помечает заказ как удалённый (is_deleted = true), не удаляя запись физически"
    )
    @Parameters({
            @Parameter(
                    name = "id",
                    description = "ID заказа для удаления",
                    required = true,
                    example = "1"
            )
    })
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Заказ успешно помечен как удалённый"),
            @ApiResponse(responseCode = "404", description = "Заказ с указанным ID не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })

    /**
     * DELETE /api/orders/{id} - Удалить заказ (Soft Delete)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        log.info("DELETE /api/orders/{} - Удаление заказа", id);
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            log.error("Заказ с ID {} не найден", id);
            return ResponseEntity.notFound().build();
        }
    }
}