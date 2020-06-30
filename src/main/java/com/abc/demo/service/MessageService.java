package com.abc.demo.service;

import com.abc.demo.controller.dto.res.ResState;
import com.abc.demo.entity.Language;
import com.abc.demo.entity.Message;
import com.abc.demo.repository.LanguageRepository;
import com.abc.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Service
public class MessageService {

    @Value("${demo.default.language-tag}")
    private String defaultLauageTag;

    @Autowired
    MessageSource messageSource;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private MessageRepository messageRepository;

    public String getMessage(ResState resState) {
        String languageTag = (String) httpSession.getAttribute("languageTag");

        Long languageId = languageRepository.findByTag(languageTag)
                .map(Language::getId).orElse(0L);

        if (languageId == 0) {
            return messageSource.getMessage(resState.getMessageKey(), null, Locale.forLanguageTag(defaultLauageTag));
        }

        return messageRepository.findByLanguageIdAndKey(languageId, resState.getMessageKey())
                .map(Message::getMessage)
                .orElseGet(() ->
                        messageSource.getMessage(resState.getMessageKey(),
                        null,
                        Locale.forLanguageTag(languageTag)));
    }

}
