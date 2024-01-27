package com.example.restaurantmanagement.domain.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProcessedEvent {
    private String orderId;
    private String restaurantId;
    private String menu;
    private Integer quantity;
}