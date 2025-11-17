package com.project.Bank_star.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {

    private static final Logger log = LoggerFactory.getLogger(Handler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> onException(Exception e) {
        log.error("Unhandled exception: {}", e.getMessage(), e);
        return ResponseEntity.status(500).body("Internal server error");
    }
}

