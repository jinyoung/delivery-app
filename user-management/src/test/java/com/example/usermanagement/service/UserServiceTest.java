package com.example.usermanagement.service;

import com.example.usermanagement.domain.Address;
import com.example.usermanagement.domain.User;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.command.RegisterUserCommand;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testRegisterUser() {
        MessageChannel mockOutputChannel = mock(MessageChannel.class);
        UserService userServiceWithMock = new UserService(userRepository, mockOutputChannel);

        Address address = new Address("123 Main St", "Springfield", "IL", "62701", "USA");
        RegisterUserCommand command = new RegisterUserCommand("John Doe", "555-1234", address);

        User registeredUser = userServiceWithMock.registerUser(command);

        ArgumentCaptor<GenericMessage> messageCaptor = ArgumentCaptor.forClass(GenericMessage.class);
        verify(mockOutputChannel).send(messageCaptor.capture());

        assertEquals("John Doe", registeredUser.getName());
        assertEquals("555-1234", registeredUser.getPhone());
        assertEquals(address, registeredUser.getAddress());
    }
}