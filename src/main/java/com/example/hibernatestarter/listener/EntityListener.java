package com.example.hibernatestarter.listener;

import com.example.hibernatestarter.event.EntityEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Zhurenkov Pavel 14.10.2023
 */
@Slf4j
@Component
public class EntityListener {

    @EventListener
    public void acceptEntity(EntityEvent entityEvent){
        log.info("Info from listener", entityEvent);

    }
}
