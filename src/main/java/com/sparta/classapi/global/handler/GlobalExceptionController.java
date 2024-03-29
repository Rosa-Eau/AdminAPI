package com.sparta.classapi.global.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionController {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(BindingResult bindingResult) {
      Map<String, String> errors = new HashMap<>();
      bindingResult.getAllErrors().forEach(c -> errors.put(((FieldError)c).getField() , c.getDefaultMessage()));

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }
}