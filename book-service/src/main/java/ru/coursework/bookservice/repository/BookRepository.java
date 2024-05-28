package ru.coursework.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.coursework.bookservice.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}

