package org.demo.app.rest.impl;

import java.util.ArrayList;
import java.util.List;

import org.demo.app.dto.Book;
import org.demo.app.rest.api.BookController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

//@Service
@RestController
public class BookControllerImpl implements BookController {

    private final List<Book> bookList = new ArrayList<>();

    @Override
    public ResponseEntity<Book> getBookById(int id) {
        return bookList.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookList);
    }

    @Override
    public ResponseEntity<Book> createBook(Book book) {
        bookList.add(book);
        return ResponseEntity.ok(book);
    }

    @Override
    public ResponseEntity<Book> updateBook(int id, Book updatedBook) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                return ResponseEntity.ok(book);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deleteBook(int id) {
        boolean removed = bookList.removeIf(book -> book.getId() == id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
