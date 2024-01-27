package com.example.deliverymanagement.service;

import com.example.deliverymanagement.command.AssignOrder;
import com.example.deliverymanagement.command.UpdateRider;
import com.example.deliverymanagement.domain.Rider;
import com.example.deliverymanagement.event.OrderAssigned;
import com.example.deliverymanagement.event.RiderUpdated;
import com.example.deliverymanagement.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class RiderService {
    private final RiderRepository riderRepository;
    private final MessageChannel outputChannel;

    @Autowired
    public RiderService(RiderRepository riderRepository, MessageChannel outputChannel) {
        this.riderRepository = riderRepository;
        this.outputChannel = outputChannel;
    }

    public void assignOrder(AssignOrder command) {
        // Logic to assign an order to a rider
        // ...

        // Publish an event
        OrderAssigned event = new OrderAssigned(command.getOrderId(), command.getRiderId());
        outputChannel.send(MessageBuilder.withPayload(event).build());
    }

    public void updateRider(UpdateRider command) {
        // Logic to update rider information
        Rider rider = riderRepository.findById(command.getRiderId()).orElseThrow();
        rider.setName(command.getName());
        rider.setPhone(command.getPhone());
        riderRepository.save(rider);

        // Publish an event
        RiderUpdated event = new RiderUpdated(command.getRiderId(), command.getName(), command.getPhone());
        outputChannel.send(MessageBuilder.withPayload(event).build());
    }
}