package com.sagar.member;

import org.springframework.web.bind.annotation.*;  // Required for @RequestMapping, @PostMapping, @GetMapping, etc.
import org.springframework.http.HttpStatus;    // Required for @ResponseStatus(HttpStatus.CREATED)
import org.springframework.http.ResponseEntity; // Required for ResponseEntity<>
import java.util.List; // Required for List<Member>
import lombok.RequiredArgsConstructor; // Required for @RequiredArgsConstructor
import com.sagar.member.MemberService;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor

public class MemberController {

    private  final MemberService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save ( @RequestBody Member member ){
        service.saveMember(member);
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers(){
        return ResponseEntity.ok(service.getAllMembers());

    }

    @GetMapping("/gym/{gym-id}")
    public ResponseEntity<List<Member>> findAllMembers(
        @PathVariable("gym-id") Integer gymId
    ){
        return ResponseEntity.ok(service.findAllMembersByGym(gymId));
    }
}