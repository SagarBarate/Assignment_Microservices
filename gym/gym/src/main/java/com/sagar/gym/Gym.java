package com.sagar.gym;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // ID will be generated automatically

    private String name;
    private String email;
}
