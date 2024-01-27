package com.example.usermanagement.policy;

import com.example.usermanagement.domain.User;
import com.example.usermanagement.events.OrderProcessedEvent;
import com.example.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessedNotificationPolicy {

    private final UserRepository userRepository;

    @Autowired
    public OrderProcessedNotificationPolicy(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener
    public void handleOrderProcessedEvent(OrderProcessedEvent event) {
        User user = userRepository.findById(event.getUserId()).orElseThrow();
        // 여기서 법칙에 따라 알림처리를 구현하세요. For example: send email, etc...
        // emailService.sendOrderProcessedNotification(user.getEmail(), event);
    }
}
