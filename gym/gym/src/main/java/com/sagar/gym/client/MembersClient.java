package com.sagar.gym.client;

import com.sagar.gym.Member;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="member-service", url="${application.config.members-url}")
public interface MembersClient{

    @GetMapping("/gym/{gym-id}")
    List<Member> fundAllMemmbersByGym(
        @PathVariable("gym-id") Integer gymId
    );

}