package com.nhnacademy.miniDooray.dtos.task;

public record TaskModifyRequestDto(
        String taskTags,
        String taskName,
        String milestone,
        String content
) {
}
