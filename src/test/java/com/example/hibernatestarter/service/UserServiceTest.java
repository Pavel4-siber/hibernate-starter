package com.example.hibernatestarter.service;

import com.example.hibernatestarter.dto.UserReadDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zhurenkov Pavel 08.10.2023
 */
@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void findUserById() {
        System.out.println(userService.findUserById(1L).get());
    }
}
