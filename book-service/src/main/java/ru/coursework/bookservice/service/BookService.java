package ru.coursework.bookservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.coursework.bookservice.model.Book;
import ru.coursework.bookservice.repository.BookRepository;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Cacheable("books")
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @CacheEvict(value = "books", allEntries = true)
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Cacheable("books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}

