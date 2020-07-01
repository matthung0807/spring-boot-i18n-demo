package com.abc.demo.controller;

import com.abc.demo.i18n.DemoReloadableResourceBundleMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.Properties;

@RestController
public class DemoController {

    @Autowired
    private DemoReloadableResourceBundleMessageSource messageSource;

    @GetMapping("/message")
    public void message() {

        String s1 = messageSource.getMessage("demo.message", null, Locale.ENGLISH);
        System.out.println(s1); // MessageSource config

        String s2 = messageSource.getMessage("demo.message", null, Locale.TAIWAN);
        System.out.println(s2); // MessageSource 配置

        String s3 = messageSource.getMessage("demo.hello-world", null, Locale.ENGLISH);
        System.out.println(s3); // Hello World

        String s4 = messageSource.getMessage("demo.hello-world", null, Locale.TAIWAN);
        System.out.println(s4); // 哈囉世界

        messageSource.clearCache();

        Properties messagesProperties = messageSource.getProperties(Locale.ENGLISH);
        messagesProperties.setProperty("demo.message", "ReloadableResourceBundleMessageSource example");

        Properties messagesZhTwProperties = messageSource.getProperties(Locale.TAIWAN);
        messagesZhTwProperties.setProperty("demo.message", "ReloadableResourceBundleMessageSource 範例");

    }

    @GetMapping("/message/new")
    public void newMessage() {
        String s1 = messageSource.getMessage("demo.message", null, Locale.ENGLISH);
        System.out.println(s1); // ReloadableResourceBundleMessageSource example

        String s2 = messageSource.getMessage("demo.message", null, Locale.TAIWAN);
        System.out.println(s2); // ReloadableResourceBundleMessageSource 範例

        String s3 = messageSource.getMessage("demo.hello-world", null, Locale.ENGLISH);
        System.out.println(s3); // Hello World

        String s4 = messageSource.getMessage("demo.hello-world", null, Locale.TAIWAN);
        System.out.println(s4); // 哈囉世界
    }

}
