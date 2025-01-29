package org.demo.app.rest.api.errors;

public class BadRequestSortByException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BadRequestSortByException(String value) {
        super("Invalid 'sort_by' parameter: '" + value + "' ");
    }
}
