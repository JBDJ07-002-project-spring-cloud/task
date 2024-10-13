package com.nhnacademy.miniDooray.service;

import com.nhnacademy.miniDooray.dto.comment.CommentCreateRequestDto;
import com.nhnacademy.miniDooray.entity.Comment;
import com.nhnacademy.miniDooray.entity.Task;

public interface CommentService {
    Comment createComment(Task task, CommentCreateRequestDto commentCreateRequestDto);
    Comment updateComment(Long commentId, String newContent);
    void deleteComment(Long commentId);

    void asdf();
}
