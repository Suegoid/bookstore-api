package com.diogo.Handles;

import com.diogo.Exceptions.ObjectNotFoundException;
import com.diogo.Exceptions.StandardError;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler {
  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e){
    StandardError standardError = new StandardError(System.currentTimeMillis(),HttpStatus.NOT_FOUND.value(),e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
  }
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException e){
    StandardError standardError = new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
  }
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException e){
    StandardError standardError = new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),e.getMessage(),e.getFieldError().getField()+" "+e.getFieldError().getDefaultMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
  }
  
}
