package main.java.ru.orel.java.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.java.ru.orel.java.dto.CreateOrderRequest;
import main.java.ru.orel.java.dto.OrderDto;
import main.java.ru.orel.java.dto.OrderItemDto;
import main.java.ru.orel.java.entity.Order;
import main.java.ru.orel.java.entity.OrderItem;
import main.java.ru.orel.java.entity.Pizza;
import main.java.ru.orel.java.repository.OrderRepository;
import main.java.ru.orel.java.repository.PizzaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

public class OrderService {

    private final OrderRepository orderRepository;
    private final PizzaRepository pizzaRepository;

    /**
     * Получить все активные заказы
     */
    @Transactional(readOnly = true)
    public List<OrderDto> getAllOrders() {
        log.debug("Получение всех активных заказов");
        return orderRepository.findAllActive().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Получить заказ по ID
     */
    @Transactional(readOnly = true)
    public OrderDto getOrderById(Long id) {
        log.debug("Получение заказа по ID: {}", id);
        Order order = orderRepository.findByIdActive(id)
                .orElseThrow(() -> new RuntimeException("Заказ с ID " + id + " не найден"));
        return convertToDto(order);
    }

    /**
     * Создать новый заказ
     */
    @Transactional
    public OrderDto createOrder(CreateOrderRequest request) {
        log.debug("Создание нового заказа для клиента: {}", request.getCustomerName());

        Order order = new Order();
        order.setCustomerName(request.getCustomerName());
        order.setCustomerPhone(request.getCustomerPhone());
        order.setStatus("NEW");
        order.setIsDeleted(false);

        BigDecimal totalPrice = BigDecimal.ZERO;

        // Добавляем позиции заказа
        for (CreateOrderRequest.OrderItemRequest itemRequest : request.getItems()) {
            Pizza pizza = pizzaRepository.findByIdActive(itemRequest.getPizzaId())
                    .orElseThrow(() -> new RuntimeException("Пицца с ID " + itemRequest.getPizzaId() + " не найдена"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPizza(pizza);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(pizza.getPrice());

            order.getItems().add(orderItem);

            // Считаем общую сумму
            BigDecimal itemTotal = pizza.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity()));
            totalPrice = totalPrice.add(itemTotal);
        }

        order.setTotalPrice(totalPrice);

        Order saved = orderRepository.save(order);
        return convertToDto(saved);
    }

    /**
     * Обновить статус заказа
     */
    @Transactional
    public OrderDto updateOrderStatus(Long id, String status) {
        log.debug("Обновление статуса заказа ID: {} на {}", id, status);
        Order order = orderRepository.findByIdActive(id)
                .orElseThrow(() -> new RuntimeException("Заказ с ID " + id + " не найден"));

        order.setStatus(status);
        Order updated = orderRepository.save(order);
        return convertToDto(updated);
    }

    /**
     * Удалить заказ (Soft Delete)
     */
    @Transactional
    public void deleteOrder(Long id) {
        log.debug("Удаление заказа ID: {}", id);
        Order order = orderRepository.findByIdActive(id)
                .orElseThrow(() -> new RuntimeException("Заказ с ID " + id + " не найден"));

        order.setIsDeleted(true);
        orderRepository.save(order);
    }

    /**
     * Получить заказы по статусу
     */
    @Transactional(readOnly = true)
    public List<OrderDto> getOrdersByStatus(String status) {
        log.debug("Получение заказов со статусом: {}", status);
        return orderRepository.findByStatusAndNotDeleted(status).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Получить заказы клиента по телефону
     */
    @Transactional(readOnly = true)
    public List<OrderDto> getOrdersByPhone(String phone) {
        log.debug("Получение заказов клиента с телефоном: {}", phone);
        return orderRepository.findByCustomerPhoneAndNotDeleted(phone).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // === Вспомогательные методы ===

    private OrderDto convertToDto(Order order) {
        List<OrderItemDto> itemDtos = order.getItems().stream()
                .map(this::convertItemToDto)
                .collect(Collectors.toList());

        return new OrderDto(
                order.getId(),
                order.getCustomerName(),
                order.getCustomerPhone(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getCreatedAt(),
                itemDtos
        );
    }

    private OrderItemDto convertItemToDto(OrderItem item) {
        return new OrderItemDto(
                item.getId(),
                item.getPizza().getId(),
                item.getPizza().getName(),
                item.getQuantity(),
                item.getPrice()
        );
    }
}
