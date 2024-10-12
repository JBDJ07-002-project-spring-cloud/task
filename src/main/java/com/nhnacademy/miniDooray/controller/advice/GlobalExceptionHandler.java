package com.nhnacademy.miniDooray.controller.advice;

import com.nhnacademy.miniDooray.dtos.message.MessageResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<MessageResponseDto> responseStatusException(ResponseStatusException e){
        MessageResponseDto messageResponseDto = new MessageResponseDto(
            e.getStatusCode().value(),e.getMessage()
        );

        return ResponseEntity.status(e.getStatusCode()).body(messageResponseDto);
    }


}
