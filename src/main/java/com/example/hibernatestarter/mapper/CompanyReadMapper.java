package com.example.hibernatestarter.mapper;

import com.example.hibernatestarter.dto.CompanyReadDto;
import com.example.hibernatestarter.model.Company;
import org.springframework.stereotype.Component;

/**
 * @author Zhurenkov Pavel 08.10.2023
 */
@Component
public class CompanyReadMapper implements Mapper<Company, CompanyReadDto>{
    @Override
    public CompanyReadDto mapFrom(Company object) {
        return new CompanyReadDto(
                object.getId(),
                object.getName()
        );
    }
}
