package org.demo.app.core.adapters.primary.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.demo.app.core.adapters.primary.BookService;
import org.demo.app.core.adapters.secondary.db.BookDb;
import org.demo.app.core.entities.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
	
	private BookDb bookDb ; // injected
	
	/**
	 * Constructor usable for Dependency Injection
	 * @param bookDb
	 */
	protected BookServiceImpl(BookDb bookDb) {
		super();
		this.bookDb = bookDb;
	}

	@Override
	public List<Book> findAll(Optional<Sort> optionalSort) {
    	logger.debug("findAll()");
    	return bookDb.selectAll(optionalSort);
	}

	@Override
	public Book findById(long id) {
    	logger.debug("findById({})", id);
    	return bookDb.selectByPK(id);
	}

	@Override
	public List<Book> findBy(String title, Map<String, Object> criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(long id, Book book) {
    	logger.debug("save({},{})", id, book);
    	bookDb.upsert(id, book);
	}

	@Override
	public boolean update(Book book) {
    	logger.debug("save({})", book);
    	return bookDb.update(book);
	}

	@Override
	public boolean partialUpdate(long id, Book dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Book book) {
    	logger.debug("create({})", book);
    	return bookDb.insert(book);
	}

	@Override
	public boolean deleteById(long id) {
    	logger.debug("deleteById({})", id);
    	return bookDb.delete(id);
	}

}
