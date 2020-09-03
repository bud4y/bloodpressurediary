package com.progmatic.bpdiary.service;

import com.progmatic.bpdiary.model.misc.DrugTakingNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugTakingNoteRepository extends JpaRepository<DrugTakingNote, Integer> {
}
