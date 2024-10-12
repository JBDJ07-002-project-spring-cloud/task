package com.nhnacademy.miniDooray.service;

import com.nhnacademy.miniDooray.dtos.task.TaskDetailResponseDto;
import com.nhnacademy.miniDooray.dtos.task.TaskModifyRequestDto;
import com.nhnacademy.miniDooray.dtos.task.TaskRegisterRequestDto;
import com.nhnacademy.miniDooray.dtos.task.TaskResponseDto;

import java.util.List;

public interface TaskService {

    public List<TaskResponseDto> getAllTasksByProjectId(long projectId);

    TaskDetailResponseDto getTaskByProjectIdAndTaskId(long projectidLong, long taskIdLong);

    void resgisterTask(long projectId, TaskRegisterRequestDto requestDto);

    void modifyTask(long projectIdLong, long taskIdLong, TaskModifyRequestDto requestDto);
}
