package com.nhnacademy.miniDooray.exception;

import com.nhnacademy.miniDooray.dto.StatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
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
}
