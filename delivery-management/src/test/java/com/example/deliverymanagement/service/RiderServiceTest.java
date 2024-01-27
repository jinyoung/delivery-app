package com.example.deliverymanagement.service;

import com.example.deliverymanagement.command.UpdateRider;
import com.example.deliverymanagement.domain.Rider;
import com.example.deliverymanagement.repository.RiderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RiderServiceTest {

    @Autowired
    private RiderService riderService;

    @Test
    public void testUpdateRider() {
        RiderRepository mockRiderRepository = mock(RiderRepository.class);
        MessageChannel mockOutputChannel = mock(MessageChannel.class);

        Rider rider = new Rider();
        rider.setRiderId("rider123");
        rider.setName("John Doe");
        rider.setPhone("1234567890");

        when(mockRiderRepository.findById("rider123")).thenReturn(java.util.Optional.of(rider));

        UpdateRider command = new UpdateRider("rider123", "Jane Doe", "0987654321");
        riderService = new RiderService(mockRiderRepository, mockOutputChannel);
        riderService.updateRider(command);

        ArgumentCaptor<Rider> riderArgumentCaptor = ArgumentCaptor.forClass(Rider.class);
        verify(mockRiderRepository).save(riderArgumentCaptor.capture());

        Rider updatedRider = riderArgumentCaptor.getValue();
        assertEquals("Jane Doe", updatedRider.getName());
        assertEquals("0987654321", updatedRider.getPhone());

        verify(mockOutputChannel).send(any(MessageBuilder.class).build());
    }
}