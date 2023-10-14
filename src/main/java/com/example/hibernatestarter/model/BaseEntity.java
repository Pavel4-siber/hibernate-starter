package com.example.hibernatestarter.model;

import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

/**
 * @author Zhurenkov Pavel 01.10.2023
 */
@MappedSuperclass
public interface BaseEntity <T extends Serializable>{

    T getId();

    void setId(T id);
}
