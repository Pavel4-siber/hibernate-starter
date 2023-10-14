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

    @Override
    public User map(UserCreateDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(UserCreateDto object, User user) {
        user.setUsername(object.username());
        user.setId(object.companyId());
        user.setRole(object.role());
    }
}

