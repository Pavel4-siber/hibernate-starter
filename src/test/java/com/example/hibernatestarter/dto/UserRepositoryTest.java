package com.example.hibernatestarter.dto;

import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zhurenkov Pavel 08.10.2023
 */
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    void findAll(){
        UserRepository repository = new UserRepository(entityManager);
        int size = repository.findAll().size();
        assertEquals(size,6);
    }

}
