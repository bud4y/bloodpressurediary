package com.progmatic.bpdiary.web.controller;

import com.progmatic.bpdiary.web.dto.MessageDTO;
import com.progmatic.bpdiary.model.forum.Message;
import com.progmatic.bpdiary.service.impl.MessageService;
import com.progmatic.bpdiary.service.impl.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class MessageController {
    @Autowired
    private final MessageService messageService;
    @Autowired
    private final TopicService topicService;

    @Autowired
    public MessageController(MessageService messageService, TopicService topicService) {
        this.messageService = messageService;
        this.topicService = topicService;
    }


    @PostMapping("/createMessage")
    public Message processNewComment(@RequestBody MessageDTO commentDto) {

        return messageService.createNewCommentOnPost(commentDto);
    }

}
