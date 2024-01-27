package com.example.usermanagement.service;

import com.example.usermanagement.domain.User;
import com.example.usermanagement.events.UserRegisteredEvent;
import com.example.usermanagement.events.UserUpdatedEvent;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.command.RegisterUserCommand;
import com.example.usermanagement.service.command.UpdateUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MessageChannel outputChannel;

    @Autowired
    public UserService(UserRepository userRepository, MessageChannel outputChannel) {
        this.userRepository = userRepository;
        this.outputChannel = outputChannel;
    }

    public User registerUser(RegisterUserCommand command) {
        User user = new User();
        user.setName(command.getName());
        user.setPhone(command.getPhone());
        user.setAddress(command.getAddress());
        User savedUser = userRepository.save(user);

        UserRegisteredEvent event = new UserRegisteredEvent(savedUser.getUserId(), savedUser.getName(), savedUser.getPhone(), savedUser.getAddress());
        outputChannel.send(MessageBuilder.withPayload(event).build());

        return savedUser;
    }

    public User updateUser(UpdateUserCommand command) {
        User user = userRepository.findById(Long.parseLong(command.getUserId())).orElseThrow();
        user.setName(command.getName());
        user.setPhone(command.getPhone());
        user.setAddress(command.getAddress());
        User updatedUser = userRepository.save(user);

        UserUpdatedEvent event = new UserUpdatedEvent(updatedUser.getUserId(), updatedUser.getName(), updatedUser.getPhone(), updatedUser.getAddress());
        outputChannel.send(MessageBuilder.withPayload(event).build());

        return updatedUser;
    }
}