package com.example.hibernatestarter.dto;

import com.example.hibernatestarter.model.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author Zhurenkov Pavel 08.10.2023
 */
public interface Repository <K extends Serializable, E extends BaseEntity<K>>{

    E save (E entity);

    void delete(K id);

    void update(E entity);

    Optional<E> findById(K id);

    List<E> findAll();
}
