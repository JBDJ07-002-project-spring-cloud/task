package com.nhnacademy.miniDooray.controller;

import com.nhnacademy.miniDooray.dtos.task.TaskDetailResponseDto;
import com.nhnacademy.miniDooray.dtos.task.TaskResponseDto;
import com.nhnacademy.miniDooray.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{projectId}")
    public List<TaskResponseDto> getAllTasksByProjectId(@PathVariable String projectId) {

        long id;

        try {
            id = Long.parseLong(projectId);
        } catch (NumberFormatException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid project ID");
        }

        return taskService.getAllTasksByProjectId(id);

    }

    @GetMapping("/{projectId}/{taskId}")
    public TaskDetailResponseDto getTaskByProjectIdAndTaskId(
            @PathVariable String projectId,
            @PathVariable String taskId){

        long projectidLong;
        long taskIdLong;

        try {
            projectidLong = Long.parseLong(projectId);
            taskIdLong = Long.parseLong(taskId);
        } catch (NumberFormatException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid project ID or task ID");
        }

        return taskService.getTaskByProjectIdAndTaskId(projectidLong, taskIdLong);

    }
}
