package com.nhnacademy.miniDooray.controller.advice;

import com.nhnacademy.miniDooray.dto.StatusResponse;
import com.nhnacademy.miniDooray.dto.message.MessageResponseArrayDto;
import com.nhnacademy.miniDooray.dto.message.MessageResponseDto;
import com.nhnacademy.miniDooray.exception.MemberAlreadyExistsInProjectException;
import com.nhnacademy.miniDooray.exception.ProjectNotFoundException;
import com.nhnacademy.miniDooray.exception.TagNotFoundInProjectException;
import com.nhnacademy.miniDooray.exception.UserNotInProjectException;
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

    @ExceptionHandler(MemberAlreadyExistsInProjectException.class)
    public ResponseEntity<StatusResponse> handleMemberAlreadyExistsInProjectException(MemberAlreadyExistsInProjectException ex) {
        StatusResponse statusResponse = new StatusResponse(400, "이미 멤버로 존재합니다.");
        return ResponseEntity.status(400).body(statusResponse);
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<StatusResponse> handleProjectNotFound(ProjectNotFoundException ex) {
        StatusResponse statusResponse = new StatusResponse(404, "프로젝트가 존재하지 않습니다.");
        return ResponseEntity.status(404).body(statusResponse);
    }

    @ExceptionHandler(TagNotFoundInProjectException.class)
    public ResponseEntity<StatusResponse> handleTagNotFoundInProject(TagNotFoundInProjectException ex) {
        StatusResponse statusResponse = new StatusResponse(404, "해당 프로젝트에 해당 태그가 존재하지 않습니다.");
        return ResponseEntity.status(404).body(statusResponse);
    }

    @ExceptionHandler(UserNotInProjectException.class)
    public ResponseEntity<StatusResponse> handleUserNotInProject(UserNotInProjectException ex) {
        StatusResponse statusResponse = new StatusResponse(404, "유저가 해당 프로젝트의 멤버가 아닙니다.");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(statusResponse);
    }

}
