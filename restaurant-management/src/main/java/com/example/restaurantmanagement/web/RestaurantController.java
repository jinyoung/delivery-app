package com.example.restaurantmanagement.web;

import com.example.restaurantmanagement.application.RestaurantService;
import com.example.restaurantmanagement.domain.commands.RegisterRestaurantCommand;
import com.example.restaurantmanagement.domain.commands.UpdateRestaurantCommand;
import com.example.restaurantmanagement.domain.events.RestaurantRegisteredEvent;
import com.example.restaurantmanagement.domain.events.RestaurantUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<RestaurantRegisteredEvent> registerRestaurant(@RequestBody RegisterRestaurantCommand command) {
        RestaurantRegisteredEvent event = restaurantService.registerRestaurant(command);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantUpdatedEvent> updateRestaurant(@PathVariable String restaurantId,
                                                                   @RequestBody UpdateRestaurantCommand command) {
        command.setRestaurantId(restaurantId);
        RestaurantUpdatedEvent event = restaurantService.updateRestaurant(command);
        return ResponseEntity.ok(event);
    }
}