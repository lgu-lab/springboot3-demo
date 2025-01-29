package org.demo.app.core.adapters.secondary.db;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.demo.app.core.entities.Book;
import org.springframework.data.domain.Sort;

public interface BookDb {

	List<Book> selectAll(Optional<Sort> optionalSort);
	
	Book selectByPK(long id);
	
	List<Book> selectWhere(String title, Map<String,Object> criteria);
	
	// Specific finders if any
	// List<Book> findByXxxx(String xxx);

	boolean insert(Book dto);

	void upsert(long id, Book dto);
	
	boolean update(Book dto);
	
	boolean update(long id, Map<String,Object> changes);
	
	boolean delete(long id);
	
}
