package com.example.hibernatestarter.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Zhurenkov Pavel 01.10.2023
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users_chat")
public class UserChat extends AuditableEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
