package com.example.hibernatestarter.dto;

import com.example.hibernatestarter.model.Role;

/**
 * @author Zhurenkov Pavel 08.10.2023
 */
public record UserReadDto(Long id,
                          String username,
                          Role role,
                          CompanyReadDto company){
}
