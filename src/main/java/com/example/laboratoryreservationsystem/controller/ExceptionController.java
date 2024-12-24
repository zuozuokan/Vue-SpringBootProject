package com.example.laboratoryreservationsystem.controller;


import com.example.laboratoryreservationsystem.exception.XException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(XException.class)
    public Map<String,Object> handleXException(XException e){
        return Map.of("message",e.getMessage());
    }
}
