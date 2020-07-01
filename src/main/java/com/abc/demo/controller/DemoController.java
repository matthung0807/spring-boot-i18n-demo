package com.abc.demo.controller;

import com.abc.demo.controller.req.UpdateMessageDto;
import com.abc.demo.i18n.DemoReloadableResourceBundleMessageSource;
import com.abc.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class DemoController {

    @Autowired
    private DemoReloadableResourceBundleMessageSource messageSource;

    @Autowired
    private MessageService messageService;

    @GetMapping("/message")
    public void message() {

        String s1 = messageSource.getMessage("demo.hello-world", null, Locale.forLanguageTag("en-US"));
        System.out.println(s1); // ZA WARUDO

        String s2 = messageSource.getMessage("demo.hello-world", null, Locale.forLanguageTag("zh-TW"));
        System.out.println(s2); // 你好世界

    }

    @PostMapping("/message/update")
    public void updateMessage(@RequestBody UpdateMessageDto updateMessageDto) {

        messageService.updateMessage(updateMessageDto);

    }

}
