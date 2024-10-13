package com.nhnacademy.miniDooray.dtos.task;

public record TaskRegisterRequestDto(
        String taskTags,
        String taskName,
        String milestone,
        String content,
        String userName
) {
}
