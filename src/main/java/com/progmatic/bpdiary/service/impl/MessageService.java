package com.progmatic.bpdiary.service.impl;

import com.progmatic.bpdiary.model.forum.Message;
import com.progmatic.bpdiary.service.MessageRepository;
import com.progmatic.bpdiary.web.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class MessageService {
    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createNewCommentOnPost(MessageDTO newCommentForm) {
        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Message comment = new Message();
        comment.setMessageTopic(newCommentForm.getPostId());
        comment.setText(newCommentForm.getText());
        comment.setAuthor(loggedInUserName);
        comment.setDeleted(false);
        comment.setDateTime(LocalDateTime.now());
        return messageRepository.save(comment);
    }

//    @Transactional(isolation = Isolation.SERIALIZABLE)
//    public Message getMessage(Long id) {
//        return em.find(Message.class, id);
//    }
//
//
//    public List<Message> getMessageList() {
//        return em.createQuery("SELECT m FROM Message m", Message.class).getResultList();
//    }
}
