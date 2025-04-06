package com.sagar.gym;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.sagar.gym.client.MembersClient;

@Service
@RequiredArgsConstructor
public class GymService {

    private final GymRepositroy repository;
    private final MembersClient client;  // Mark as final for constructor injection

    public void saveGym(Gym gym) {
        repository.save(gym);
    }

    public List<Gym> getAllGyms() {
        return repository.findAll();
    }

    public FullGymResponse findGymsWithMembers(Integer gymId) {
        var gym = repository.findById(gymId)
            .orElse(
                Gym.builder()
                    .name("NOT_FOUND")
                    .email("NOT_FOUND")
                    .build()
            );

        // Call the client (ensure method name is consistent)
        var members = client.findAllMembersByGym(gymId);
        return FullGymResponse.builder()
            .name(gym.getName())
            .email(gym.getEmail())
            .members(members)
            .build();
    }
}
