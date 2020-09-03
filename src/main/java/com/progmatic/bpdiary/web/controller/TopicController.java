package com.progmatic.bpdiary.web.controller;

import com.progmatic.bpdiary.model.forum.Topic;
import com.progmatic.bpdiary.service.impl.TopicService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class TopicController {

    TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/listOfTopics")
    public List<Topic> getTopic() {
        return topicService.listAllTopics();
    }


    @GetMapping("/newTopic")
    public Topic createTopic(@RequestBody Topic newTopic) {
        topicService.creatingTopic(newTopic);
        return newTopic;
    }
}
