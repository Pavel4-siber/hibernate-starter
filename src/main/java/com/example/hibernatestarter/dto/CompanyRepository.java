package com.example.hibernatestarter.dto;

import com.example.hibernatestarter.model.Company;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

/**
 * @author Zhurenkov Pavel 08.10.2023
 */
@Repository
public class CompanyRepository extends BaseRepository<Long, Company>{

    public CompanyRepository(EntityManager entityManager) {
        super(entityManager, Company.class);
    }
}
