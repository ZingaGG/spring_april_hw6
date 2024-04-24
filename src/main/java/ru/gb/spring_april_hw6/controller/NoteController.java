package ru.gb.spring_april_hw6.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring_april_hw6.model.Note;
import ru.gb.spring_april_hw6.service.NoteService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


/**
 * 1. Добавление заметки. (Подсказка @PostMapping )
 * 2. Просмотр всех заметок.(Подсказка @GetMapping )
 * 3. Получение заметки по id. (Подсказка @GetMapping("/{id}") )
 * 4. Редактирование заметки.(Подсказка @PutMapping("/{id}") )
 * 5. Удаление заметки.(Подсказка @DeleteMapping("/{id}") )
 */
@RestController
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        Optional<List<Note>> optionalNotes = noteService.getAllNotes();

        if (optionalNotes.isPresent()) {
            List<Note> notes = optionalNotes.get();
            return new ResponseEntity<>(notes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note){
        return new ResponseEntity<>(noteService.createNote(note), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> findNoteById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(noteService.findById(id).orElseThrow(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note updatedNote) {
        try {
            Note updated = noteService.updateNoteById(updatedNote, id).orElseThrow();
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Note> deleteNoteById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(noteService.deleteNoteById(id).orElseThrow(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
