package com.sagar.member;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.json.JsonWriter.Members;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    public void saveMember(Member member) {
        repository.save(member);
    }

    public List<Member> getAllMembers() {  // Renamed to match MemberController
        return repository.findAll();  // Correct return statement
    }

@CircuitBreaker(name = "memberServiceCB", fallbackMethod = "fallbackForFindAll")
public List<Member> findAllMembersByGym(Integer gymId) {
    if (gymId == 999) {
        throw new RuntimeException("Trigger circuit breaker");
    }
    return repository.findAllByGymId(gymId);
}
    
}
