package cz.muni.fi.pa165.mm.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Václav Stehlík; 487580
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="The requested resource was not found.")
public class ResourceNotFoundException extends RuntimeException {
    
} 
