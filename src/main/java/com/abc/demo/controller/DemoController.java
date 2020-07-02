package com.abc.demo.controller;

import com.abc.demo.controller.req.UpdateMessageDto;
import com.abc.demo.i18n.DemoReloadableResourceBundleMessageSource;
import com.abc.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@RestController
public class DemoController {

    @Autowired
    private DemoReloadableResourceBundleMessageSource messageSource;

    @Autowired
    private MessageService messageService;

    @GetMapping("/message")
    public void message() {

        String s1 = messageSource.getMessage("demo.message", null, Locale.forLanguageTag("en-US"));
        System.out.println(s1);

        String s2 = messageSource.getMessage("demo.message", null, Locale.forLanguageTag("zh-TW"));
        System.out.println(s2);

        String s3 = messageSource.getMessage("demo.hello-world", null, Locale.forLanguageTag("en-US"));
        System.out.println(s3);

        String s4 = messageSource.getMessage("demo.hello-world", null, Locale.forLanguageTag("zh-TW"));
        System.out.println(s4);

    }

    @GetMapping("/message/{languageTag}")
    public List<String> getPropertiesKeyList(@PathVariable String languageTag) {
        Set<String> propertiesKeySet = messageService.getPropertiesKeySet(Locale.forLanguageTag(languageTag));
        return new ArrayList<>(propertiesKeySet);
    }

    @PostMapping("/message/update")
    public void updateMessage(@RequestBody UpdateMessageDto updateMessageDto) {

        messageService.updateMessageProperties(updateMessageDto);

    }

}
