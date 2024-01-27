package com.example.restaurantmanagement.application;

import com.example.restaurantmanagement.domain.Restaurant;
import com.example.restaurantmanagement.domain.RestaurantRepository;
import com.example.restaurantmanagement.domain.commands.RegisterRestaurantCommand;
import com.example.restaurantmanagement.domain.commands.UpdateRestaurantCommand;
import com.example.restaurantmanagement.domain.events.RestaurantRegisteredEvent;
import com.example.restaurantmanagement.domain.events.RestaurantUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public RestaurantRegisteredEvent registerRestaurant(RegisterRestaurantCommand command) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(command.getRestaurantId());
        restaurant.setName(command.getName());
        restaurant.setAddress(command.getAddress());
        restaurant.setPhone(command.getPhone());
        restaurant.setMenu(command.getMenu());
        restaurantRepository.save(restaurant);

        return new RestaurantRegisteredEvent(
                restaurant.getRestaurantId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getPhone(),
                restaurant.getMenu()
        );
    }

    @Transactional
    public RestaurantUpdatedEvent updateRestaurant(UpdateRestaurantCommand command) {
        Restaurant restaurant = restaurantRepository.findById(command.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        restaurant.setName(command.getName());
        restaurant.setAddress(command.getAddress());
        restaurant.setPhone(command.getPhone());
        restaurant.setMenu(command.getMenu());
        restaurantRepository.save(restaurant);

        return new RestaurantUpdatedEvent(
                restaurant.getRestaurantId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getPhone(),
                restaurant.getMenu()
        );
    }
}