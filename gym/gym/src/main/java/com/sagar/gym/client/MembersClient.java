package com.sagar.gym.client;

import com.sagar.gym.Member;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import java.util.Collections;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="member-service", url="${application.config.members-url}")
@CircuitBreaker(name = "membersService", fallbackMethod = "membersFallback")
@Retry(name = "membersServiceRetry")
public interface MembersClient{


    @GetMapping("/gym/{gym-id}")
    @CircuitBreaker(name = "membersService", fallbackMethod = "membersFallback")
    @Retry(name = "membersServiceRetry")
    List<Member> findAllMembersByGym(@PathVariable("gym-id") Integer gymId);

    // Fallback method (default implementation inside interface)
    default List<Member> membersFallback(Integer gymId, Throwable throwable) {
        System.out.println("Fallback triggered due to: " + throwable.getMessage());
        return Collections.emptyList(); // Graceful fallback response
    }


}