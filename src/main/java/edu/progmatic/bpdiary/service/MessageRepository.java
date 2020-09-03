package edu.progmatic.bpdiary.service;

import edu.progmatic.bpdiary.model.forum.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
