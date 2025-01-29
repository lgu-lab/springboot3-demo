package org.demo.app.db.jpa.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.demo.app.core.adapters.secondary.db.BookDb;
import org.demo.app.core.entities.Book;
import org.demo.app.db.jpa.commons.GenericService;
import org.demo.app.db.jpa.entities.BookEntity;
import org.demo.app.db.jpa.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookDbImpl extends GenericService<BookEntity, Book> implements BookDb {
	
	private static final Logger logger = LoggerFactory.getLogger(BookDbImpl.class);

	private final BookRepository bookRepository; // injected by constructor
	
	/**
	 * Constructor usable for Dependency Injection
	 * 
	 * @param bookRepository the repository to be injected
	 */
	public BookDbImpl(BookRepository bookRepository) {
		super(BookEntity.class, Book.class);
		this.bookRepository = bookRepository;
	}
	
	@Override
	public List<Book> selectAll(Optional<Sort> optionalSort) {
		logger.debug("findAll()");
		if ( optionalSort.isPresent() ) {
			// with sorting
			return entityListToDtoList( bookRepository.findAll(optionalSort.get()) );
		}
		else {
			// no sorting
			return entityListToDtoList( bookRepository.findAll() );
		}
	}

	@Override
	public Book selectByPK(long id) {
		logger.debug("findById({})", id);
		return entityToDto(bookRepository.findById(id));
	}

	@Override
	public List<Book> selectWhere(String title, Map<String, Object> criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Book book) {
		logger.debug("create({})", book);
		if (bookRepository.existsById(book.getId())) {
			return false; // already exists, not created
		} else {
			bookRepository.save(dtoToEntity(book));
			return true; // created
		}
	}

	@Override
	public void upsert(long id, Book dto) {
		logger.debug("save({},{})", id, dto);
		dto.setId(id); 
		bookRepository.save(dtoToEntity(dto));
	}

	@Override
	public boolean update(Book dto) {
		logger.debug("update({})", dto);
		if (bookRepository.existsById(dto.getId())) {
			bookRepository.save(dtoToEntity(dto));
			return true; // find and updated
		} else {
			return false; // not found (not updated)
		}
	}

	@Override
	public boolean update(long id, Map<String, Object> changes) {
		// TODO 
		return false;
	}

	@Override
	public boolean delete(long id) {
		logger.debug("deleteById({})", id);
		if (bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
			return true; // find and deleted
		} else {
			return false; // not found (not deleted)
		}
	}

}
