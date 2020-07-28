package edu.progmatic.blood_pressure_diary.controllers;

import edu.progmatic.blood_pressure_diary.dtos.MessageDTO;
import edu.progmatic.blood_pressure_diary.models.forum.Message;
import edu.progmatic.blood_pressure_diary.services.MessageService;
import edu.progmatic.blood_pressure_diary.services.TopicService;
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
