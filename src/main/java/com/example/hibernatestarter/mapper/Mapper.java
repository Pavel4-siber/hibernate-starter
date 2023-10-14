package com.example.hibernatestarter.mapper;

import com.example.hibernatestarter.dto.UserCreateDto;
import com.example.hibernatestarter.model.User;

/**
 * @author Zhurenkov Pavel 08.10.2023
 */
public interface Mapper <F, T>{
    T mapFrom(F object);

    default T map(F fromObject, T toObject){
        return toObject;
    }
}
