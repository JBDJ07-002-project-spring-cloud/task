package com.nhnacademy.miniDooray.dtos.task;

import com.nhnacademy.miniDooray.stubs.task.CommentResponseDtoStub;

import java.util.List;

public record TaskDetailResponseDto(

        String projectName,
        String taskName,
        String content,
        String taskTags,
        String taskMilstone,
        String userName,
        List<CommentResponseDtoStub> comments

) {
}
