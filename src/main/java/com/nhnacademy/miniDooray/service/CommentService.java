package com.nhnacademy.miniDooray.service;

import com.nhnacademy.miniDooray.entity.Comment;
import com.nhnacademy.miniDooray.entity.Task;
import com.nhnacademy.miniDooray.entity.User;
import com.nhnacademy.miniDooray.repository.CommentRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Task task, String content, User user){
        Comment comment = Comment.builder()
                .task(task)
                .user(user)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, String newContent) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID"));
        comment.setContent(newContent);
        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {

        commentRepository.deleteById(commentId);
    }

    public List<Comment> getCommentsByTask(Task task) {
        return commentRepository.findByTask(task);
    }

}
