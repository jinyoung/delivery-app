package com.example.deliverymanagement.command;

import lombok.Value;

@Value
public class UpdateRider {
    String riderId;
    String name;
    String phone;
}