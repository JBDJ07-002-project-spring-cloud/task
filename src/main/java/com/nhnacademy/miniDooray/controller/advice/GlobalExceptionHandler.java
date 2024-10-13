package com.nhnacademy.miniDooray.controller.advice;

import com.nhnacademy.miniDooray.dto.message.MessageResponseDto;
import com.nhnacademy.miniDooray.dto.message.MessageResponseArrayDto;
import com.nhnacademy.miniDooray.exception.MemberAlreadyExistsInProjectException;
import com.nhnacademy.miniDooray.exception.ProjectNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<com.nhnacademy.miniDooray.dto.message.MessageResponseDto> responseStatusException(ResponseStatusException e){
        com.nhnacademy.miniDooray.dto.message.MessageResponseDto messageResponseDto = new com.nhnacademy.miniDooray.dto.message.MessageResponseDto(
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

    @ExceptionHandler(MemberAlreadyExistsInProjectException.class)
    public ResponseEntity<MessageResponseDto> handleMemberAlreadyExistsInProjectException(MemberAlreadyExistsInProjectException ex) {
        MessageResponseDto statusResponse = new MessageResponseDto(400, "이미 멤버로 존재합니다.");
        return ResponseEntity.status(400).body(statusResponse);
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<MessageResponseDto> handleProjectNotFound(ProjectNotFoundException ex) {
        MessageResponseDto statusResponse = new MessageResponseDto(404, "프로젝트가 존재하지 않습니다.");
        return ResponseEntity.status(404).body(statusResponse);
    }


}
