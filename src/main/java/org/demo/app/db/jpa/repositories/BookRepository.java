package org.demo.app.db.jpa.repositories;

import java.util.List;

import org.demo.app.db.jpa.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for entity "xx" <br> 
 * 
 * This repository extends PagingAndSortingRepository interface <br>
 * so it provides by default all the basic CRUD operations : <br>
 *   findById, findAll, save, delete, etc <br> 
 * with pagination and sorting : <br>
 *   findAll(Pageable), findAll(Sort)<br>
 * 
 * This repository can be extended by adding specific "finders" methods<br>
 * To do so, see the "predicates conventions" for "derived query methods" in Spring Data documentation
 * 
 * @author xx
 *
 */
public interface BookRepository extends JpaRepository<BookEntity, Long> {

	// Insert specific finders here 
	List<BookEntity> findByTitle(String title);

//	List<Book> findByTitleStartingWith(String titlePart);
//
//	List<Book> findByTitleContaining(String titlePart);
//
//	List<Book> findByPrice(BigDecimal price);
//
//	List<Book> findByTitleContainingAndPrice(String titlePart, BigDecimal price);
}