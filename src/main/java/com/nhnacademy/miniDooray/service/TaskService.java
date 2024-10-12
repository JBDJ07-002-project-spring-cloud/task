package com.nhnacademy.miniDooray.service;

import com.nhnacademy.miniDooray.dtos.task.TaskResponseDto;

import java.util.List;

public interface TaskService {

    public List<TaskResponseDto> getAllTasksByProjectId(long projectId);
}
