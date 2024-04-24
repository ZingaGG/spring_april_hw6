package ru.gb.spring_april_hw6.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.spring_april_hw6.model.Note;
import ru.gb.spring_april_hw6.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

/**
 * 1. Добавление заметки. (Подсказка @PostMapping )
 * 2. Просмотр всех заметок.(Подсказка @GetMapping )
 * 3. Получение заметки по id. (Подсказка @GetMapping("/{id}") )
 * 4. Редактирование заметки.(Подсказка @PutMapping("/{id}") )
 * 5. Удаление заметки.(Подсказка @DeleteMapping("/{id}") )
 */

@Service
@AllArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public Note createNote(Note note){
        return noteRepository.save(note);
    }

    public Optional<List<Note>> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        return Optional.ofNullable(notes.isEmpty() ? null : notes);
    }


    /**
     * Подсказка:
     * Репозиторий насладует JpaRepository<Note, Long>. В репозитории добавляем метод Optional<Note> findById(Long id);
     */

    public Optional<Note> findById(Long id){
        return noteRepository.findById(id);
    }

    public Optional<Note> updateNoteById(Note updatedNote, Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);

        if (optionalNote.isEmpty()) {
            return Optional.empty();
        }

        Note noteToUpdate = optionalNote.get();

        noteToUpdate.setTitle(updatedNote.getTitle());
        noteToUpdate.setDescription(updatedNote.getDescription());

        Note savedNote = noteRepository.save(noteToUpdate);

        return Optional.of(savedNote);
    }

    public Optional<Note> deleteNoteById(Long id){
        Optional<Note> optionalNote = noteRepository.findById(id);

        if(optionalNote.isEmpty()){
            return Optional.empty();
        }

        noteRepository.deleteById(id);

        return optionalNote;
    }



}
