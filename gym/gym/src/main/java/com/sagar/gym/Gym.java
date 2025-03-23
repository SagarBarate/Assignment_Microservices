package com.sagar.gym;

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
    private Integer id;
    private String name;
    private String email;
}
