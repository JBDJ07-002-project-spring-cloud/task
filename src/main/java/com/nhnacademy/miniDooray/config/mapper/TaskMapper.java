package com.nhnacademy.miniDooray.config.mapper;

import com.nhnacademy.miniDooray.dtos.task.TaskResponseDto;
import com.nhnacademy.miniDooray.entity.Task;

public class TaskMapper {

    public static TaskResponseDto toResponseDto(Task task, String tagStrings){
        return new TaskResponseDto(
                task.getProject().getProjectName(),
                task.getTaskContent(),
                tagStrings,
                task.getMilestone().getMilestoneName(),
                task.getUser().getUserName()
        );
    }
}
