package org.demo.app.core.adapters.primary;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.demo.app.core.entities.Book;
import org.springframework.data.domain.Sort;

public interface BookService {

	List<Book> findAll(Optional<Sort> optionalSort);
	
	Book findById(long id);
	
	List<Book> findBy(String title, Map<String,Object> criteria);
	
	// Specific finders if any
	// List<Book> findByXxxx(String xxx);

	void save(long id, Book dto);
	
	boolean update(Book dto);
	
	boolean partialUpdate(long id, Book dto);
	
	boolean create(Book dto);
	
	boolean deleteById(long id);
	
}
