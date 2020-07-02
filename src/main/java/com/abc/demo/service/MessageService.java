package com.abc.demo.service;

import com.abc.demo.controller.req.KeyValue;
import com.abc.demo.controller.req.UpdateMessageDto;
import com.abc.demo.entity.Language;
import com.abc.demo.i18n.DemoReloadableResourceBundleMessageSource;
import com.abc.demo.repository.LanguageRepository;
import com.abc.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Set;

@Service
public class MessageService {

    @Autowired
    private DemoReloadableResourceBundleMessageSource messageSource;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private MessageRepository messageRepository;

    public Set<String> getPropertiesKeySet(Locale locale) {
        return messageSource.getPropertiesKeySet(locale);
    }

    public void updateAllMessagePropertiesFromDatabase() {
        messageRepository.findAll().forEach(message -> {
            Locale locale = languageRepository.findById(message.getLanguageId())
                    .map(language -> Locale.forLanguageTag(language.getTag()))
                    .orElseThrow(AssertionError::new);
            messageSource.updateProperty(locale, message.getKey(), message.getMessage());
        });
    }

    public void updateMessageProperties(UpdateMessageDto updateMessageDto) {
        languageRepository.findByTag(updateMessageDto.getLanguageTag())
                .ifPresent(language ->
                        updateMessageDto.getKeyValueList().forEach(
                                keyValue -> updateMessageProperty(language, keyValue)));

    }

    public void updateMessageProperty(Language language, KeyValue<String, String> keyValue) {
        messageRepository.findByLanguageIdAndKey(language.getId(), keyValue.getKey())
                .ifPresent(message -> {
                    message.setMessage(keyValue.getValue());
                    messageRepository.save(message);
                    messageSource.updateProperty(
                            Locale.forLanguageTag(language.getTag()),
                            keyValue.getKey(), keyValue.getValue());
                });
    }

}
