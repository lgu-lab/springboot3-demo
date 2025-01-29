package org.demo.app.rest.api.commons;

import java.util.Optional;

import org.demo.app.rest.api.errors.BadRequestSortByException;
import org.springframework.data.domain.Sort;

public class SortParser {
	
    public static Optional<Sort> buildSort(String sortBy, Class<?> clazz) {
    	Optional<Sort> optionalSort = buildSort(sortBy);
        // Validate sorting properties
        if (!SortValidator.checkProperties(optionalSort, clazz)) {
        	// Error, invalid 'sort_by' 
        	throw new BadRequestSortByException(sortBy);
        }
        else {
        	// OK, valid 'sort_by' 
            return optionalSort;
        }
    }
    
    protected static Optional<Sort> buildSort(String sortBy) {
        // If 'sort_by' is null or empty, return an empty Sort (i.e., no sorting)
        if (sortBy == null || sortBy.trim().isEmpty()) {
        	return Optional.empty();
        }

        // Split the 'sort_by' parameter by commas
        String[] fields = sortBy.split(",");
        if ( fields.length == 0 ) {
        	return Optional.empty();
        }

        // Create Sort.Order objects for each field, assuming default ascending order
        Sort.Order[] orders = new Sort.Order[fields.length];
        for (int i = 0; i < fields.length; i++) {
            // Add support for ascending or descending by adding logic here, for simplicity, it's default ASC
            orders[i] = buildSortOrder(fields[i]);
        }

        // Return the complete Sort object
        return Optional.of(Sort.by(orders));
    }
    
    private static final String DESC = "desc" ;
    
    protected static Sort.Order buildSortOrder(String s1) {
    	if ( s1 == null ) {
    		return null ;
    	}  	
    	String s = s1.trim();
    	if ( s.isEmpty() || s.isBlank() ) {
    		return null ;
    	}
    	int index = s.indexOf('.');
    	if ( index < 0 ) {
    		// no separator ( "asc" by default )
    		return Sort.Order.asc(s);
    	}
    	else {
    		// split in 2 parts 
    		String property  = s.substring(0, index);
    		String direction = s.substring(index + 1);
    		if ( DESC.equals(direction) ) {
        		return Sort.Order.desc(property);
    		}
    		else { // asc for all other values (default)
        		return Sort.Order.asc(property);
    		}    			
    	}
    }

}
