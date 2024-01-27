package com.example.deliverymanagement.command;

import lombok.Value;

@Value
public class AssignOrder {
    String orderId;
    String riderId;
}