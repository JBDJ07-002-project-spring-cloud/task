package com.nhnacademy.miniDooray.controller;

import com.nhnacademy.miniDooray.dto.comment.CommentCreateRequestDto;
import com.nhnacademy.miniDooray.dto.comment.CommentUpdateRequestDto;
import com.nhnacademy.miniDooray.dto.message.MessageResponseDto;
import com.nhnacademy.miniDooray.entity.Comment;
import com.nhnacademy.miniDooray.entity.Task;
import com.nhnacademy.miniDooray.service.CommentService;
import com.nhnacademy.miniDooray.service.TaskService;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/task/{projectId}/{taskId}")
public class CommentController {
    private final CommentService commentService;
    private final TaskService taskService;
    private final HttpMessageConverters messageConverters;


    public CommentController(CommentService commentService, TaskService taskService, HttpMessageConverters messageConverters) {
        this.commentService = commentService;
        this.taskService = taskService;
        this.messageConverters = messageConverters;
    }

    @PostMapping("/comment")
    public MessageResponseDto createComment(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @RequestBody CommentCreateRequestDto commentRequest) {

        Task task = taskService.getTaskById(projectId, taskId);
        if(task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid task ID");
        }
        Comment newComment = commentService.createComment(task, projectId, commentRequest);
        return new MessageResponseDto(200, "OK");
    }


    @PatchMapping("/{commentId}")
    public MessageResponseDto updateComment(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequestDto commentRequest) {

        commentService.updateComment(projectId, taskId,commentId, commentRequest.content());
        return new MessageResponseDto(200, "OK");
    }

    @DeleteMapping("/{commentId}")
    public MessageResponseDto deleteComment(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @PathVariable Long commentId) {

        commentService.deleteComment(projectId, taskId,commentId);
        return new MessageResponseDto(200, "OK");
    }

}
