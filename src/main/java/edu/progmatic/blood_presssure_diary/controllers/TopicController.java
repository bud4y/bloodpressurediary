package edu.progmatic.blood_presssure_diary.controllers;

import edu.progmatic.blood_presssure_diary.models.forum.Topic;
import edu.progmatic.blood_presssure_diary.services.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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