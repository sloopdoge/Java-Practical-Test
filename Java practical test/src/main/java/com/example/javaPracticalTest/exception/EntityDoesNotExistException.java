package com.example.javaPracticalTest.exception;

public class EntityDoesNotExistException extends RuntimeException{
  public EntityDoesNotExistException(String message) {
    super(message);
  }

}
