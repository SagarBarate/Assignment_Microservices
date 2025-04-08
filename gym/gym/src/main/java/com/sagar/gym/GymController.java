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

    @GetMapping("/test")
	public String testok(){
		return "OK";
	}

    @GetMapping
    public ResponseEntity<List<Gym>> getAllGyms(){
        return ResponseEntity.ok(service.getAllGyms());

    }
//     @GetMapping
//     public ResponseEntity<List<Gym>> getAllGyms(){
//         List<Gym> dummyGyms = List.of(new Gym(1, "Test Gym", "Test Address"));
//         return ResponseEntity.ok(dummyGyms);
// }

    @GetMapping("/with-members/{gym-id}")
    public ResponseEntity<FullGymResponse> getAllGyms(
        @PathVariable("gym-id") Integer gymId
    ){
        return ResponseEntity.ok(service.findGymsWithMembers(gymId));

    }

}