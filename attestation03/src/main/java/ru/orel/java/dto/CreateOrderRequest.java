package main.java.ru.orel.java.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
public class CreateOrderRequest {
    private String customerName;
    private String customerPhone;
    private List<OrderItemRequest> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemRequest {
        private Long pizzaId;
        private Integer quantity;
    }
}
