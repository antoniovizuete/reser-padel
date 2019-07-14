package com.reser.padel.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.reser.padel.controllers")
public class RestExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleHttpMessageNotReadableException(HttpServletResponse response, HttpMessageNotReadableException ex) throws IOException {
    	response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMostSpecificCause().getMessage());
    }
    
}
