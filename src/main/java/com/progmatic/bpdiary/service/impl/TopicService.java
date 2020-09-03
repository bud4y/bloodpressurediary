package com.progmatic.bpdiary.service.impl;

import com.progmatic.bpdiary.model.forum.Topic;
import com.progmatic.bpdiary.service.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TopicService {

    TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public void creatingTopic(Topic topic) {
        topicRepository.save(topic);
    }


    public List<Topic> listAllTopics() {
        return  topicRepository.findAll();
    }
}
