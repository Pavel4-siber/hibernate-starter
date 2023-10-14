package com.example.hibernatestarter.dto;

import lombok.Builder;
import lombok.Value;

/**
 * @author Zhurenkov Pavel 07.10.2023
 */
@Value
@Builder
public class UserFilter {

    String firstname;

    String lastname;
}
