package com.nhnacademy.miniDooray.service.impl;

import com.nhnacademy.miniDooray.dto.comment.CommentCreateRequestDto;
import com.nhnacademy.miniDooray.entity.Comment;
import com.nhnacademy.miniDooray.entity.Task;
import com.nhnacademy.miniDooray.entity.User;
import com.nhnacademy.miniDooray.repository.CommentRepository;
import com.nhnacademy.miniDooray.repository.UserRepository;
import com.nhnacademy.miniDooray.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public Comment createComment(Task task, CommentCreateRequestDto commentCreateRequestDto){

        User user = userRepository.findUserByUserName(commentCreateRequestDto.getUserName());

        Comment comment = Comment.builder()
                .task(task)
                .user(user) //유저객체 받아올 메서드 이용해서 User 받아옴
                .content(commentCreateRequestDto.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long commentId, String newContent) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID"));
        comment.setContent(newContent);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {

        commentRepository.deleteById(commentId);
    }

    public List<Comment> getCommentsByTask(Task task) {
        return commentRepository.findByTask(task);
    }

}
