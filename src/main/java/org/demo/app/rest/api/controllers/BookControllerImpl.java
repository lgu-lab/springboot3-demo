package org.demo.app.rest.api.controllers;

import java.util.List;
import java.util.Optional;

import org.demo.app.core.adapters.primary.BookService;
import org.demo.app.core.entities.Book;
import org.demo.app.rest.api.BookController;
import org.demo.app.rest.api.commons.SortParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookControllerImpl implements BookController {

	private static final Logger logger = LoggerFactory.getLogger(BookControllerImpl.class);
	
	private BookService bookService ; // injected
	
	/**
	 * Constructor usable for Dependency Injection
	 * @param bookService
	 */
	protected BookControllerImpl(BookService bookService) {
		super();
		this.bookService = bookService;
	}

    @Override
    public ResponseEntity<Book> getById(int id) {
    	logger.debug("getBookById({})", id);
    	Book book = bookService.findById(id);
		if ( book != null ) {
			return ResponseEntity.ok(book); // 200 OK, found
		}
		else {
			return ResponseEntity.notFound().build(); // 404 Not found
		}
    }

    @Override
    public ResponseEntity<List<Book>> getAll(String sortBy) {
    	logger.debug("getAll()");
    	Optional<Sort> optionalSort = SortParser.buildSort(sortBy, Book.class);
//        // Validate sorting properties
//        if (!SortValidator.checkProperties(optionalSort, Book.class)) {
//        	throw new BadRequestSortByException(sortBy);
//        }
    	List<Book> list = bookService.findAll(optionalSort);
    	return ResponseEntity.ok(list); // always 200  (void list if none)
    }

    @Override
    public ResponseEntity<Book> post(Book book) {
    	logger.debug("create({})", book);
		if ( bookService.create(book) ) {
			return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 created
		}
		else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict
		}    	
    }

    @Override
    public ResponseEntity<Book> putWithId(int id, Book book) {
    	logger.debug("save({},{})", id, book);
		bookService.save(id, book);
		return ResponseEntity.ok().build(); // OK, updated or created
    }

    @Override
    public ResponseEntity<Void> deleteById(int id) {
    	logger.debug("httpRestDelete({})", id);
		if ( bookService.deleteById(id) ) {
			return ResponseEntity.noContent().build(); // 204 No content = "deleted"
		}
		else {
			return ResponseEntity.notFound().build(); // 404 Not found = "not deleted"
		}    	
    }
    
}
