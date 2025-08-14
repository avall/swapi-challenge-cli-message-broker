package com.capitole.challenge.cli.application.exception;

/**
 * @author alex.vall
 * Exception thrown when a resource is not found.
 *
 * @since 1.0.0
 */
public class EntityNotFoundException extends CoreException {
  public EntityNotFoundException(String message) {
    super(message);
  }

  public EntityNotFoundException(String message, String code) {
    super(message, code);
  }
}
