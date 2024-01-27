package com.example.deliverymanagement.event;

import lombok.Value;

@Value
public class RiderUpdated {
    String riderId;
    String name;
    String phone;
}