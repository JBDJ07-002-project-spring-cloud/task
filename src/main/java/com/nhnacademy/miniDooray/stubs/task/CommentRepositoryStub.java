package com.nhnacademy.miniDooray.stubs.task;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentRepositoryStub {
    public List<CommentStub> findByTaskId(long taskId) {
        List<CommentStub> commentList = new ArrayList<>();
        if(taskId == 1) {
            commentList.add(new CommentStub(1, "testUser1", "This is a comment on Task 1", "2024-10-01 10:00:00"));
            commentList.add(new CommentStub(2, "testUser1", "This is a comment on Task 2", "2024-10-01 10:00:00"));
        }
        return commentList;
    }
}
