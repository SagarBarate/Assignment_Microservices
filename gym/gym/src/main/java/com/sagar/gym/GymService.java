package com.sagar.gym;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import com.sagar.gym.client.MembersClient;

@Service
@RequiredArgsConstructor
public class GymService {

    private final GymRepositroy repository;
    private final MembersClient client;

    public void saveGym(Gym gym) {
        repository.save(gym);
    }

    public List<Gym> getAllGyms() {
        return repository.findAll();
    }

    /**
     * This method retrieves a gym and calls an external member service.
     * It is protected by a circuit breaker and retry mechanism.
     */
    @CircuitBreaker(name = "membersService", fallbackMethod = "fallbackForFindGymsWithMembers")
    @Retry(name = "membersServiceRetry")
    public FullGymResponse findGymsWithMembers(Integer gymId) {
        // Retrieve gym details or build a default if not found
        Gym gym = repository.findById(gymId)
                .orElse(
                    Gym.builder()
                        .name("NOT_FOUND")
                        .email("NOT_FOUND")
                        .build()
                );
        
        // Call the external Members service using the Feign client
        List<Member> members = client.findAllMembersByGym(gymId);
        
        // Build and return the full gym response
        return FullGymResponse.builder()
                .name(gym.getName())
                .email(gym.getEmail())
                .members(members)
                .build();
    }

    /**
     * Fallback method invoked when findGymsWithMembers fails.
     * It must have the same parameters as the original method, plus a Throwable.
     */
    public FullGymResponse fallbackForFindGymsWithMembers(Integer gymId, Throwable t) {
        System.err.println("Fallback triggered for findGymsWithMembers: " + t.getMessage());
        return FullGymResponse.builder()
                .name("Fallback Gym")
                .email("fallback@example.com")
                .members(Collections.emptyList())
                .build();
    }
}
