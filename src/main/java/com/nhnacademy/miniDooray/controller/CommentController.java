package com.nhnacademy.miniDooray.controller;

import com.nhnacademy.miniDooray.dto.comment.CommentCreateRequestDto;
import com.nhnacademy.miniDooray.dto.comment.CommentResponse;
import com.nhnacademy.miniDooray.dto.comment.CommentUpdateRequestDto;
import com.nhnacademy.miniDooray.entity.Comment;
import com.nhnacademy.miniDooray.entity.Task;
import com.nhnacademy.miniDooray.service.CommentService;
import com.nhnacademy.miniDooray.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task/{projectId}/{taskId}")
public class CommentController {
    private final CommentService commentService;
    private final TaskService taskService;


    public CommentController(CommentService commentService, TaskService taskService) {
        this.commentService = commentService;
        this.taskService = taskService;
    }

    @PostMapping("/comment")
    public ResponseEntity<?> createComment(
            @PathVariable Long ProjectId,
            @PathVariable Long taskId,
            @RequestBody CommentCreateRequestDto commentRequest) {


        // 1. 커맨트 생성 요청
        // 2. 어디 task에 속하는지 확인
        // 3.
        Task task = taskService.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task ID"));


        Comment newComment = commentService.createComment(task, commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommentResponse(200, "OK"));

    }


    @PatchMapping("/{commentId}")
    public ResponseEntity<?> updateComment(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequestDto commentRequest) {

        commentService.updateComment(commentId, commentRequest.getContent());
        return ResponseEntity.ok(new CommentResponse(200, "OK"));
    }

    @DeleteMapping("/{commentId}/")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @PathVariable Long commentId) {

        commentService.deleteComment(commentId);
        return ResponseEntity.ok(new CommentResponse(200, "ok"));
    }

}
