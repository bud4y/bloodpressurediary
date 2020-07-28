package edu.progmatic.blood_pressure_diary.repositories;

import edu.progmatic.blood_pressure_diary.models.forum.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository  extends JpaRepository<Topic, Long> {

}
