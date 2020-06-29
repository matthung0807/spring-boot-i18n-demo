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
    public void message() {
        String s1 = messageSource.getMessage("demo.message", null, Locale.ENGLISH);
        System.out.println(s1); // A simple message

        String s2 = messageSource.getMessage("demo.message.args", new String[]{"hello", "world"}, Locale.TAIWAN);
        System.out.println(s2); // A message with args, arg_0=hello, arg_1=world
    }
}
