package org.demo.app.rest.api.errors;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global Exception Handler for REST controllers 
 * It catches exceptions globally for all controllers.
 * It formats error responses consistently.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(BadRequestException ex) {
        return buildBadRequestResponse(ex);
    }

    @ExceptionHandler(BadRequestSortByException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(BadRequestSortByException ex) {
        return buildBadRequestResponse(ex);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        
        // Add a general error message
        errors.put("error", "Bad Request (invalid values in request body)");
        
//        // Collect detailed errors from the validation
//        Map<String, String> fieldErrors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error -> 
//            fieldErrors.put(error.getField(), error.getDefaultMessage())
//        );
//        // Add detailed errors to the response
//        errors.put("validation_errors", fieldErrors);
        errors.put("validation_errors", buildFieldErrors(ex));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    private Map<String, String> buildFieldErrors(MethodArgumentNotValidException ex) {
        // Collect detailed errors from the validation
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            fieldErrors.put(error.getField(), error.getDefaultMessage())
        );
        return fieldErrors;
    }

    /**
     * Returns a '400 - Bad Request' status with an error message in the response body
     * @param ex
     * @return
     */
    private ResponseEntity<Map<String, String>> buildBadRequestResponse(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        // Http Response Code = 400 (Bad Request) 
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
