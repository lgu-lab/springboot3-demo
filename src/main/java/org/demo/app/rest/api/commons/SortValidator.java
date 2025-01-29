package org.demo.app.rest.api.commons;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;

public class SortValidator {
	
	protected static boolean checkProperties(Optional<Sort> optionalSort, Class<?> clazz) {
    	if ( optionalSort.isEmpty() ) {
    		return true;
    	}
    	return checkProperties(optionalSort, getEntityFields(clazz));
    }
    
    protected static boolean checkProperties(Optional<Sort> optionalSort, Collection<String> validProperties) {
    	if ( optionalSort.isEmpty() ) {
    		return true;
    	}
        for (Sort.Order order : optionalSort.get() ) {
            if (!validProperties.contains(order.getProperty())) {
                return false;
            }
        }
        return true;
    }
    
    protected static Set<String> getEntityFields(Class<?> entityClass) {
        return List.of(entityClass.getDeclaredFields())
                .stream()
                .map(Field::getName)
                .collect(Collectors.toSet());
    }

}
