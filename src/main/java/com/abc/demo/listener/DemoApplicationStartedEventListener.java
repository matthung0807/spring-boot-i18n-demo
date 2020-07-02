package com.abc.demo.listener;

import com.abc.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DemoApplicationStartedEventListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private MessageService messageService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        messageService.updateAllMessagePropertiesFromDatabase();
    }
}
