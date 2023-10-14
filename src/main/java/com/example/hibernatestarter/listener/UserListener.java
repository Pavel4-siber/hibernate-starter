package com.example.hibernatestarter.listener;

import com.example.hibernatestarter.model.User;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author Zhurenkov Pavel 14.10.2023
 */
public class UserListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        event.getTimestamp();
    }
}
