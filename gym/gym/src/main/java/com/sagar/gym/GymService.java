package com.sagar.gym;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GymService {

    private final GymRepositroy repository;

    public void saveGym(Gym gym) {
        repository.save(gym);
    }

    public List<Gym> getAllGyms() {  // Renamed to match MemberController
        return repository.findAll();  // Correct return statement
    }

    public FullGymResponse findGymsWithMembers(Integer gymId) {
        // TODO Auto-generated method stub
        return null;
    }
}
