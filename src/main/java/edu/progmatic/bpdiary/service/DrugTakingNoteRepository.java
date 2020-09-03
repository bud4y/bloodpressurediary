package edu.progmatic.bpdiary.service;

import edu.progmatic.bpdiary.model.misc.DrugTakingNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugTakingNoteRepository extends JpaRepository<DrugTakingNote, Integer> {
}
