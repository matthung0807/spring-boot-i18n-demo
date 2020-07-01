package com.abc.demo.repository;

import com.abc.demo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findByLanguageIdAndKey(Long languageId, String key);

}
