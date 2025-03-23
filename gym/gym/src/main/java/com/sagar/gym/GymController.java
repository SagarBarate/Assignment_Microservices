package com.sagar.gym;

import org.springframework.web.bind.annotation.*;  // Required for @RequestMapping, @PostMapping, @GetMapping, etc.
import org.springframework.http.HttpStatus;    // Required for @ResponseStatus(HttpStatus.CREATED)
import org.springframework.http.ResponseEntity; // Required for ResponseEntity<>
import java.util.List; // Required for List<Member>
import lombok.RequiredArgsConstructor; // Required for @RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/gyms")
@RequiredArgsConstructor

public class GymController {

    private  final com.sagar.gym.GymService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save ( @RequestBody Gym gym ){
        service.saveGym(gym);
    }

    @GetMapping
    public ResponseEntity<List<Gym>> getAllGyms(){
        return ResponseEntity.ok(service.getAllGyms());

    }
}