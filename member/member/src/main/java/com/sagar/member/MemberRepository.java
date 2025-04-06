package com.sagar.member;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
        List<Member> findAllByGymId(Integer gymId);
        
}

