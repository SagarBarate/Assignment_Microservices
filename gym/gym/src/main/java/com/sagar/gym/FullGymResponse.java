package com.sagar.gym;


import lombok.*;
import java.util.List; // for List, if used

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FullGymResponse{

    private String name;
    private String email;

    List<Member> members;

}