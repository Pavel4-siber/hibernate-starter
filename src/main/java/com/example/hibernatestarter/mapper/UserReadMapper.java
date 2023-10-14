package com.example.hibernatestarter.mapper;

import com.example.hibernatestarter.dto.UserReadDto;
import com.example.hibernatestarter.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Zhurenkov Pavel 08.10.2023
 */
@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto>{

    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserReadDto mapFrom(User object) {
        return new UserReadDto(
                object.getId(),
                object.getUsername(),
                object.getRole(),
                companyReadMapper.mapFrom(object.getCompany()
        ));
    }
}
