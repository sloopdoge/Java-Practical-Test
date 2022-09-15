package com.example.javaPracticalTest.exception;

public class IncorrectUserAgeException extends RuntimeException{
  public IncorrectUserAgeException(String message) {
    super(message);
  }

}
