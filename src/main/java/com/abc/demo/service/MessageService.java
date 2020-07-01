package com.abc.demo.service;

import com.abc.demo.controller.req.UpdateMessageDto;
import com.abc.demo.i18n.DemoReloadableResourceBundleMessageSource;
import com.abc.demo.repository.LanguageRepository;
import com.abc.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageService {

    @Autowired
    private DemoReloadableResourceBundleMessageSource messageSource;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private MessageRepository messageRepository;

    public void updateMessageProperties() {
        messageRepository.findAll().forEach(e -> {
            Locale locale = languageRepository.findById(e.getLanguageId())
                    .map(language -> Locale.forLanguageTag(language.getTag()))
                    .orElseThrow(AssertionError::new);
            messageSource.updateProperties(locale, e.getKey(), e.getMessage());
        });
    }

    public void updateMessage(UpdateMessageDto updateMessageDto) {
        languageRepository.findByTag(updateMessageDto.getLanguageTag())
                .flatMap(e -> messageRepository.findByLanguageIdAndKey(e.getId(), updateMessageDto.getKey()))
                .ifPresent(e -> {
                    e.setMessage(updateMessageDto.getValue());
                    messageRepository.save(e);
                    updateMessageProperties();
                });

    }
}
