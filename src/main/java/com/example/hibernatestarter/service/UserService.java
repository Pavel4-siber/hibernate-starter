package com.example.hibernatestarter.service;

import com.example.hibernatestarter.dto.UserCreateDto;
import com.example.hibernatestarter.dto.UserReadDto;
import com.example.hibernatestarter.dto.UserRepository;
import com.example.hibernatestarter.mapper.Mapper;
import com.example.hibernatestarter.mapper.UserCreateMapper;
import com.example.hibernatestarter.mapper.UserReadMapper;
import com.example.hibernatestarter.model.User;
import jakarta.validation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * @author Zhurenkov Pavel 08.10.2023
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateMapper userCreateMapper;
    private final Mapper mapper;

    public Long create(UserCreateDto userDto){
        var validatorFactory = Validation.buildDefaultValidatorFactory();
        var validator = validatorFactory.getValidator();
        var violations = validator.validate(userDto);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }

        User userEntity = userCreateMapper.mapFrom(userDto);
        return userRepository.save(userEntity).getId();
    }

    public void update(UserCreateDto userDto){
        User user = userCreateMapper.mapFrom(userDto);
        User userEntity = userRepository.findById(user.getId()).get();
        mapper.copy(userEntity,userDto);
        userRepository.update(userEntity);
    }

    public boolean delete(Long id){
        Optional<User> maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.delete(id));
        return maybeUser.isPresent();
    }

    public Optional<UserReadDto> findUserById(Long id){
        return userRepository.findById(id).map(userReadMapper::mapFrom);
    }
}
