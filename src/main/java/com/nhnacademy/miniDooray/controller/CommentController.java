package com.nhnacademy.miniDooray.controller;

import com.nhnacademy.miniDooray.dto.CommentCreateRequestDto;
import com.nhnacademy.miniDooray.dto.CommentResponse;
import com.nhnacademy.miniDooray.dto.CommentUpdateRequestDto;
import com.nhnacademy.miniDooray.entity.Comment;
import com.nhnacademy.miniDooray.entity.Task;
import com.nhnacademy.miniDooray.entity.User;
import com.nhnacademy.miniDooray.service.CommentService;
import com.nhnacademy.miniDooray.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task/{projectId}/{taskId}")
public class CommentController {
//    private final CommentService commentService;
//    private final TaskService taskService;
//    private final UserService userService;
//
//    public CommentController(CommentService commentService, TaskService taskService, UserService userService) {
//        this.commentService = commentService;
//        this.taskService = taskService;
//        this.userService = userService;
//    }
//
//    @PostMapping("/comment")
//    public ResponseEntity<?> createComment(
//            @PathVariable Long ProjectId,
//            @PathVariable Long taskId,
//            @RequestBody CommentCreateRequestDto commentRequest) {
//
//        Task task = taskService.findById(taskId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid Task ID"));
//        User user = userService.findByUserName(commentRequest.getUserId())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid User ID"));
//
//        Comment newComment = commentService.createComment(task, commentRequest.getContent(), user);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(new CommentResponse(200, "OK"));
//
//    }
//
//    @PutMapping("/{commentId}")
//    public ResponseEntity<?> updateComment(
//            @PathVariable Long projectId,
//            @PathVariable Long taskId,
//            @PathVariable Long commentId,
//            @RequestBody CommentUpdateRequestDto commentRequest) {
//
//        commentService.updateComment(commentId, commentRequest.getContent());
//        return ResponseEntity.ok(new CommentResponse(200, "OK"));
//    }
//
//    @DeleteMapping("/{commentId}/")
//    public ResponseEntity<?> deleteComment(
//            @PathVariable Long projectId,
//            @PathVariable Long taskId,
//            @PathVariable Long commentId) {
//
//        commentService.deleteComment(commentId);
//        return ResponseEntity.ok(new CommentResponse(200, "ok"));
//    }

}
