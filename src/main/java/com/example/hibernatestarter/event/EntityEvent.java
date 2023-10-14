package com.example.hibernatestarter.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author Zhurenkov Pavel 14.10.2023
 */

public class EntityEvent extends ApplicationEvent{

    @Getter
    private AccessType accessType;

    public EntityEvent(Object source, AccessType accessType) {
        super(source);
        this.accessType = accessType;
    }
}
