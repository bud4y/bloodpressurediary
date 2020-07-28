package edu.progmatic.blood_pressure_diary.repositories;

import edu.progmatic.blood_pressure_diary.models.misc.DrugTakingNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugTakingNoteRepository extends JpaRepository<DrugTakingNote, Integer> {
}
