package com.progmatic.bpdiary.service;

import com.progmatic.bpdiary.model.forum.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
