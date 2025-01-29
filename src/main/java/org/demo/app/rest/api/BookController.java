package org.demo.app.rest.api;

import java.util.List;

import org.demo.app.core.entities.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@RequestMapping("/api/v1/books")
public interface BookController {

	@GetMapping("/{id}")
	ResponseEntity<Book> getById(@PathVariable int id);

	@GetMapping
	ResponseEntity<List<Book>> getAll(@RequestParam(required = false) String sort_by);

	@PostMapping
	ResponseEntity<Book> post(@Valid @RequestBody Book book);

	@PutMapping("/{id}")
	ResponseEntity<Book> putWithId(@PathVariable int id, @Valid @RequestBody Book book);

	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteById(@PathVariable int id);
}
