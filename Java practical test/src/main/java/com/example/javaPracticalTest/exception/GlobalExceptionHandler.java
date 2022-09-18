package com.example.javaPracticalTest.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityDoesNotExistException.class)
  public ResponseEntity<?> handleEntityDoesNotExistException
      (EntityDoesNotExistException exception, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(),
        exception.getMessage(),
        request.getDescription(false));
    return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(IncorrectInputDateException.class)
  public ResponseEntity<?> handleIncorrectInputDateException
      (IncorrectInputDateException exception, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(),
        exception.getMessage(),
        request.getDescription(false));
    return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IncorrectFormatDateException.class)
  public ResponseEntity<?> handleIncorrectFormatDateException
      (IncorrectFormatDateException exception, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(),
        exception.getMessage(),
        request.getDescription(false));
    return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IncorrectUsersAgeException.class)
  public ResponseEntity<?> handleIncorrectUsersAgeException
      (IncorrectUsersAgeException exception, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(),
        exception.getMessage(),
        request.getDescription(false));
    return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidFormatException.class)
  public ResponseEntity<?> handleInvalidFormatException
      (InvalidFormatException exception, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
    return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleGlobalException
      (Exception exception, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(),
        exception.getMessage(),
        request.getDescription(false));
    return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
