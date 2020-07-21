package edu.progmatic.blood_presssure_diary.repositories;

import edu.progmatic.blood_presssure_diary.models.forum.Message;
import edu.progmatic.blood_presssure_diary.models.measurement.MeasurementDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
