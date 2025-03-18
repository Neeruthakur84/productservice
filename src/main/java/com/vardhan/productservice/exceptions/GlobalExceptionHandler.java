package com.vardhan.productservice.exceptions;

import com.vardhan.productservice.dtos.ErrorDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ErrorDto mullPointerExceptionHandler() {

        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("NullPointer Exception Occurred");
        errorDto.setStatus("Failure");
        return errorDto;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDto handleProductNotFoundException(ProductNotFoundException ex) {

        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        errorDto.setStatus("Failure");
        return errorDto;

    }
}
