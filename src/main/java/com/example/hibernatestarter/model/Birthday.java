package com.example.hibernatestarter.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author Zhurenkov Pavel 08.10.2023
 */
public record Birthday (LocalDate birthDate){

    public long getAge(){
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }
}
