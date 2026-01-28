package com.jannesh.exception;

import com.jannesh.exception.customer.ContactAlreadyExistsException;
import com.jannesh.exception.customer.EmailAlreadyExistsException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> emailAlreadyExistsExceptionHandler(EmailAlreadyExistsException e) {
        Map<Object, Object> errorObj = new LinkedHashMap<>();
        errorObj.put("statusCode", 400);
        errorObj.put("errorMessage", "Email Already Exists");
        errorObj.put("errorInformation", "Kindly enter another email address");

        return new ResponseEntity<>(errorObj, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContactAlreadyExistsException.class)
    public ResponseEntity<?> contactAlreadyExistsExceptionHandler(ContactAlreadyExistsException e) {
        Map<Object, Object> errorObj = new LinkedHashMap<>();
        errorObj.put("statusCode", 400);
        errorObj.put("errorMessage", "Contact Already Exists");
        errorObj.put("errorInformation", "Kindly enter another contact number");

        return new ResponseEntity<>(errorObj, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleBodyValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("statusCode", 400);
        errors.put("ErrorMessage", "Bad Request");
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleParamValidation(ConstraintViolationException ex) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("statusCode", 400);
        errors.put("ErrorMessage", "Bad Request");
        ex.getConstraintViolations()
                .forEach(v -> errors.put(v.getPropertyPath().toString(), v.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
