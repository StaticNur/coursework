package ru.coursework.bookservice;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.coursework.bookservice.model.Book;
import ru.coursework.bookservice.repository.BookRepository;
import ru.coursework.bookservice.service.BookService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testAddAndGetBook() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        bookService.addBook(book);

        Book fetchedBook = bookService.getBookById(book.getId());
        assertEquals(book.getTitle(), fetchedBook.getTitle());
        assertEquals(book.getAuthor(), fetchedBook.getAuthor());
    }

    @Test
    public void testGetAllBooks() {
        bookService.addBook(new Book(1L, "Book 1", "Author 1"));
        bookService.addBook(new Book(2L,"Book 2", "Author 2"));

        List<Book> books = bookService.getAllBooks();
        assertEquals(2, books.size());
    }

    @Test
    public void testCacheableGetBookById() {
        Book book = new Book();
        book.setTitle("Cached Book");
        book.setAuthor("Cached Author");
        bookService.addBook(book);

        Book firstFetch = bookService.getBookById(book.getId());
        Book secondFetch = bookService.getBookById(book.getId());

        verify(bookRepository, times(1)).findById(book.getId());
    }
}

