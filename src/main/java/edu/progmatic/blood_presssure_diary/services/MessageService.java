package edu.progmatic.blood_presssure_diary.services;

import edu.progmatic.blood_presssure_diary.dtos.MessageDTO;
import edu.progmatic.blood_presssure_diary.models.forum.Message;
import edu.progmatic.blood_presssure_diary.repositories.MessageRepository;
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

}