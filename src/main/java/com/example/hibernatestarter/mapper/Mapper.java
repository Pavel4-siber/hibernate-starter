package com.example.hibernatestarter.mapper;

import com.example.hibernatestarter.dto.UserCreateDto;
import com.example.hibernatestarter.model.User;

/**
 * @author Zhurenkov Pavel 08.10.2023
 */
public interface Mapper <F, T>{
    T mapFrom(F object);

    default void copy(User user, UserCreateDto userDto){
        user.setUsername(userDto.username());
        user.setRole(userDto.role());
        user.getCompany().setId(user.getId());
    }
}
