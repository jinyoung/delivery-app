package com.example.deliverymanagement.event;

import lombok.Value;

@Value
public class OrderAssigned {
    String orderId;
    String riderId;
}