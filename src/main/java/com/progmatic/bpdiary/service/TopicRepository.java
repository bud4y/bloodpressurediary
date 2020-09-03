package com.progmatic.bpdiary.service;

import com.progmatic.bpdiary.model.forum.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository  extends JpaRepository<Topic, Long> {

}
