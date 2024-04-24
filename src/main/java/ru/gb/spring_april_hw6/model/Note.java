package ru.gb.spring_april_hw6.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/** Структура заметки:
 - ID (автоинкрементное)(тип - Long)
 - Заголовок (не может быть пустым)(тип - String)
 - Содержимое (не может быть пустым)(тип - String)
 - Дата создания (автоматически устанавливается при создании заметки)(тип - LocalDateTime)
 */
@Data
@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true, name = "date_time")
    private LocalDateTime dateTime = LocalDateTime.now();
}
