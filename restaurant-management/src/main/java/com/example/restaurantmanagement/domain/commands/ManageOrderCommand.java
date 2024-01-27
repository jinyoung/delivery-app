package com.example.restaurantmanagement.domain.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManageOrderCommand {
    private String orderId;
    private String restaurantId;
    private String menu;
    private Integer quantity;
}