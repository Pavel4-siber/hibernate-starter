package com.example.hibernatestarter.mapper;

import com.example.hibernatestarter.dto.CompanyRepository;
import com.example.hibernatestarter.dto.UserCreateDto;
import com.example.hibernatestarter.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Zhurenkov Pavel 08.10.2023
 */
@Component
@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User>{

    private final CompanyRepository companyRepository;

    @Override
    public User mapFrom(UserCreateDto object) {
        return User.builder()
                .username(object.username())
                .role(object.role())
                .company(companyRepository.findById(object.companyId())
                        .orElseThrow(IllegalArgumentException::new))
                .build();
    }
}
