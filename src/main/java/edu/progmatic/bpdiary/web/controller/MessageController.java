package edu.progmatic.bpdiary.web.controller;

import edu.progmatic.bpdiary.web.dto.MessageDTO;
import edu.progmatic.bpdiary.model.forum.Message;
import edu.progmatic.bpdiary.service.impl.MessageService;
import edu.progmatic.bpdiary.service.impl.TopicService;
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
