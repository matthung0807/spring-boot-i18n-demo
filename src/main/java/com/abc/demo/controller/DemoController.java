package com.abc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class DemoController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/message")
    public String message() {
        String message = messageSource.getMessage("demo.simple", null, Locale.ENGLISH);
        System.out.println(message);
        return message;
    }
}
