package com.example.restaurantmanagement.domain.events;

import com.example.restaurantmanagement.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantUpdatedEvent {
    private String restaurantId;
    private String name;
    private Address address;
    private String phone;
    private String menu;
}