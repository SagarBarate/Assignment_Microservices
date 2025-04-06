package com.sagar.gym;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.sagar.gym.client.MembersClient;

@Service
@RequiredArgsConstructor
public class GymService {

    private final GymRepositroy repository;
    private MembersClient client;

    public void saveGym(Gym gym) {
        repository.save(gym);
    }

    public List<Gym> getAllGyms() {  // Renamed to match MemberController
        return repository.findAll();  // Correct return statement
    }

    public FullGymResponse findGymsWithMembers(Integer gymId) {
        // TODO Auto-generated method stub
        var gym = repository.findById(gymId)
        .orElse(
            Gym.builder()
            .name("NOT_FOUND")
            .email("NOT_FOUND")
            .build()
        );

        var members = client.fundAllMemmbersByGym(gymId); //find all members from the member microservice;
        return FullGymResponse.builder()
            .name(gym.getName())
            .email(gym.getEmail())
            .members(members)
            .build();
    }
}
