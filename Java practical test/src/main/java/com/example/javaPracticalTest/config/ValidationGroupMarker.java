package com.example.javaPracticalTest.config;

public interface ValidationGroupMarker {
  public interface Update extends Default {
  }

  public interface Create extends Default {
  }

  public interface Default extends ValidationGroupMarker {
  }

}
