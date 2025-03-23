package com.sagar.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
}
