package com.nhnacademy.miniDooray.controller.advice;

import com.nhnacademy.miniDooray.dtos.message.MessageResponseArrayDto;
import com.nhnacademy.miniDooray.dtos.message.MessageResponseDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<MessageResponseDto> responseStatusException(ResponseStatusException e){
        MessageResponseDto messageResponseDto = new MessageResponseDto(
            e.getStatusCode().value(),e.getReason()
        );

        return ResponseEntity.status(e.getStatusCode()).body(messageResponseDto);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<MessageResponseArrayDto> constraintViolationException(ConstraintViolationException e) {

        List<String> errorMessages = new ArrayList<>();

        e.getConstraintViolations().forEach(violation -> {
            String errorMessage = violation.getMessage();
            errorMessages.add(errorMessage);
        });

        MessageResponseArrayDto responseDto = new MessageResponseArrayDto(400, errorMessages);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }


}
