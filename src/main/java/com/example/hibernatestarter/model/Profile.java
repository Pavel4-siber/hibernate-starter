package com.example.hibernatestarter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zhurenkov Pavel 01.10.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Profile implements BaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String language;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user){
        this.user = user;
        user.setProfile(this);
    }
}
