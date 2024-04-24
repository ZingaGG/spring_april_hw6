package ru.gb.spring_april_hw6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.spring_april_hw6.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
