package com.hotelreview.ratings.exception;

import com.hotelreview.ratings.dto.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDto> resourceNotFoundException(ResourceNotFoundException exception){
        ApiResponseDto apiResponseDto = ApiResponseDto.builder().message(exception.getMessage()).status(false).build();
        return new ResponseEntity<>(apiResponseDto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgsNotValidException(MethodArgumentNotValidException exception){
        Map<String, String> exceptionResponse = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error)->{
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            exceptionResponse.put(field, message);
        });
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
