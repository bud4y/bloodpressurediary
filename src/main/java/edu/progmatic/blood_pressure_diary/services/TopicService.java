package edu.progmatic.blood_pressure_diary.services;

import edu.progmatic.blood_pressure_diary.models.forum.Topic;
import edu.progmatic.blood_pressure_diary.repositories.TopicRepository;
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
