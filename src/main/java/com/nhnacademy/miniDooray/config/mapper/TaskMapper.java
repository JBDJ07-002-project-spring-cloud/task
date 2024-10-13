package com.nhnacademy.miniDooray.config.mapper;

import com.nhnacademy.miniDooray.dto.task.TaskDetailResponseDto;
import com.nhnacademy.miniDooray.dto.task.TaskResponseDto;
import com.nhnacademy.miniDooray.entity.Task;
import com.nhnacademy.miniDooray.stubs.task.CommentResponseDtoStub;

import java.util.List;

public class TaskMapper {

    public static TaskResponseDto toResponseDto(Task task, String tagStrings){
        return new TaskResponseDto(
                task.getProject().getProjectName(),
                task.getTaskName(),
                tagStrings,
                task.getMilestone().getMilestoneName(),
                task.getUser().getUserName()
        );
    }

    public static TaskDetailResponseDto toDetailResponseDto(Task task, String tagStrings, List<CommentResponseDtoStub> commentList) {
        return new TaskDetailResponseDto(
                task.getProject().getProjectName(),
                task.getTaskName(),
                task.getTaskContent(),
                tagStrings,
                task.getMilestone().getMilestoneName(),
                task.getUser().getUserName(),
                commentList
        );
    }

}
