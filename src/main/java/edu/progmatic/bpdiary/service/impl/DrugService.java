package edu.progmatic.bpdiary.service.impl;

import edu.progmatic.bpdiary.model.misc.DrugTakingNote;
import edu.progmatic.bpdiary.service.DrugTakingNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DrugService {
    DrugTakingNoteRepository drugTakingNoteRepository;

    @Autowired
    public DrugService(DrugTakingNoteRepository drugTakingNoteRepository) {
        this.drugTakingNoteRepository = drugTakingNoteRepository;
    }

    public DrugTakingNote createNewNote(String nameOfDrug, int everyGivenHours) {
        DrugTakingNote drugTakingNote = new DrugTakingNote();
        drugTakingNote.setNameOfDrug(nameOfDrug);
        drugTakingNote.setEveryGivenHours(everyGivenHours);
        drugTakingNoteRepository.save(drugTakingNote);
        return drugTakingNote;
    }

    public void removeNote(Integer noteId) {
        drugTakingNoteRepository.deleteById(noteId);
    }

    public DrugTakingNote updateNote(Integer noteId, String nameOfDrug, int everyGivenHours) {
        Optional<DrugTakingNote> note = drugTakingNoteRepository.findById(noteId);
        if (!note.get().getNameOfDrug().equals(nameOfDrug)) {
            note.get().setNameOfDrug(nameOfDrug);
        }
        if (note.get().getEveryGivenHours() != everyGivenHours) {
            note.get().setEveryGivenHours(everyGivenHours);
        }
        drugTakingNoteRepository.save(note.get());
        return note.get();
    }
}
