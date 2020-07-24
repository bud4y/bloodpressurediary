package edu.progmatic.blood_presssure_diary.repositories;

import edu.progmatic.blood_presssure_diary.models.forum.Message;
import edu.progmatic.blood_presssure_diary.models.forum.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository  extends JpaRepository<Topic, Long> {

}
