package com.nhnacademy.miniDooray.dtos.task;

import com.nhnacademy.miniDooray.stubs.dtos.CommentResponseDtoStub;

import java.util.List;

public record TaskDetailResponseDto(

        String projectName,
        String content,
        String taskTags,
        String taskMilstone,
        String userName,
        List<CommentResponseDtoStub> comments

) {
}
