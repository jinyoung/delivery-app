package com.example.usermanagement.service.command;

import com.example.usermanagement.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserCommand {
    private String userId;
    private String name;
    private String phone;
    private Address address;
}