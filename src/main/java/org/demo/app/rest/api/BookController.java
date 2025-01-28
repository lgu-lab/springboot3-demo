package org.demo.app.rest.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.demo.app.dto.Book;

@RequestMapping("/api/v1/books")
public interface BookController {

	@GetMapping("/{id}")
	ResponseEntity<Book> getBookById(@PathVariable int id);

	@GetMapping
	ResponseEntity<List<Book>> getAllBooks();

	@PostMapping
	ResponseEntity<Book> createBook(@RequestBody Book book);

	@PutMapping("/{id}")
	ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book);

	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteBook(@PathVariable int id);
}
