package com.nhnacademy.miniDooray.stubs.dtos;

public record CommentStub(
        long taskId,
        String userName,
        String content,
        String createdAt
) {
}
