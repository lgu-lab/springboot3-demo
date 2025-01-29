package org.demo.app.rest.api.commons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

class SortParserTest {

	@Test
	void testBuildSortOrder() {
		assertNull ( SortParser.buildSortOrder(null) ) ;
		assertNull ( SortParser.buildSortOrder("") ) ;
		assertNull ( SortParser.buildSortOrder("  ") ) ;
		
		Sort.Order o = SortParser.buildSortOrder("abc");
		assertEquals("abc", o.getProperty());
		assertEquals(Sort.Direction.ASC, o.getDirection());

		o = SortParser.buildSortOrder("abc.asc");
		assertEquals("abc", o.getProperty());
		assertEquals(Sort.Direction.ASC, o.getDirection());

		o = SortParser.buildSortOrder("abc.desc");
		assertEquals("abc", o.getProperty());
		assertEquals(Sort.Direction.DESC, o.getDirection());

		o = SortParser.buildSortOrder("abc.");
		assertEquals("abc", o.getProperty());
		assertEquals(Sort.Direction.ASC, o.getDirection());

		o = SortParser.buildSortOrder("abc.xxx");
		assertEquals("abc", o.getProperty());
		assertEquals(Sort.Direction.ASC, o.getDirection());

		o = SortParser.buildSortOrder("abc. xxx");
		assertEquals("abc", o.getProperty());
		assertEquals(Sort.Direction.ASC, o.getDirection());
	}

	@Test
	void testBuildSort() {
		assertTrue ( SortParser.buildSort(null).isEmpty() ) ;
		assertTrue ( SortParser.buildSort("").isEmpty() ) ;
		assertTrue ( SortParser.buildSort("  ").isEmpty() ) ;

		Optional<Sort> optionalSort =  SortParser.buildSort("aaa,bbb") ;
		Sort sort = optionalSort.get();
		assertTrue(sort.isSorted() );
		assertTrue(sort.toSet().size() == 2 );
		for ( Sort.Order order : sort.toList() ) {
			assertTrue ( order.getProperty().equals("aaa") || order.getProperty().equals("bbb") );
			assertTrue ( order.getDirection() == Sort.Direction.ASC );
		}

		optionalSort =  SortParser.buildSort("aaa.desc , bbb.asc") ;
		sort = optionalSort.get();
		assertTrue(sort.isSorted() );
		assertTrue(sort.toSet().size() == 2 );
		for ( Sort.Order order : sort.toList() ) {
			if ( order.getProperty().equals("aaa") ) {
				assertTrue ( order.getDirection() == Sort.Direction.DESC );
			}
			else if ( order.getProperty().equals("bbb") ) {
				assertTrue ( order.getDirection() == Sort.Direction.ASC );
			}
			else {
				fail("Unexpected property " + order.getProperty() );
			}
			
		}
	}
}
