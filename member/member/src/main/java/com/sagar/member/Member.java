package com.sagar.member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer memberId;
}
