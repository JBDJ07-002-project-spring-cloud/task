package com.nhnacademy.miniDooray.repository;

import com.nhnacademy.miniDooray.entity.Comment;
import com.nhnacademy.miniDooray.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // select * from Comment where comment_id = ?
//    Optional<Comment> findByTask(Task task);
//    // select id, content from Comment;
//    // 원래는 select * from Comment
//    CommentResponse findAllBy();

    List<Comment> findByTask(Task task);
}
