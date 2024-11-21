package com.devops.user_service_api_1.adviser;

import com.devops.user_service_api_1.exceptions.DuplicateEntryException;
import com.devops.user_service_api_1.exceptions.EntryNotFoundException;
import com.devops.user_service_api_1.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler( EntryNotFoundException.class )
    public ResponseEntity<StandardResponse> handleEntryNotFoundException(EntryNotFoundException entryNotFoundException){
        return new ResponseEntity<>(
                new StandardResponse(404, entryNotFoundException.getMessage(),entryNotFoundException),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DuplicateEntryException.class )
    public ResponseEntity<StandardResponse> handleDuplicateEntryException(DuplicateEntryException duplicateEntryException){
        return new ResponseEntity<>(
                new StandardResponse(409, duplicateEntryException.getMessage(),duplicateEntryException),
                HttpStatus.CONFLICT
        );
    }
}
