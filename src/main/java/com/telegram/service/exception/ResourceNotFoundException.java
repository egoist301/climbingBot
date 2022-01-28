package com.telegram.service.exception;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(Long id) {
    this("Resource with id=" + id + " does not exist.");
  }

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public ResourceNotFoundException(Throwable cause) {
    super(cause);
  }

  public ResourceNotFoundException() {
  }
}
