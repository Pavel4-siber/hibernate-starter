package com.example.hibernatestarter.dto;

import com.example.hibernatestarter.model.Role;
import jakarta.validation.constraints.NotNull;

/**
 * @author Zhurenkov Pavel 08.10.2023
 */
public record UserCreateDto (@NotNull
                             String username,
                             Role role,
                             Long companyId)
{
}
