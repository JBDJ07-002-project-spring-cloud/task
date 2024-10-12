package com.nhnacademy.miniDooray.service.impl;

import com.nhnacademy.miniDooray.config.mapper.TaskMapper;
import com.nhnacademy.miniDooray.dtos.task.TaskResponseDto;
import com.nhnacademy.miniDooray.entity.ProjectTag;
import com.nhnacademy.miniDooray.entity.Tag;
import com.nhnacademy.miniDooray.entity.Task;
import com.nhnacademy.miniDooray.repository.ProjectTagRepository;
import com.nhnacademy.miniDooray.repository.TaskRepository;
import com.nhnacademy.miniDooray.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectTagRepository projectTagRepository;

    @Override
    public List<TaskResponseDto> getAllTasksByProjectId(long projectId) {
        List<Task> taskList = taskRepository.findByProjectId(projectId);

        return taskList.stream()
                .map(this::convertToTaskResponseDto)
                .collect(Collectors.toList());
    }

    private TaskResponseDto convertToTaskResponseDto(Task task) {
        List<ProjectTag> tagList = projectTagRepository.findTagByTaskId(task.getId());
        String tagStrings = tagList.stream()
                .map(ProjectTag -> ProjectTag.getTag().getTagName())
                .collect(Collectors.joining(", "));
        return TaskMapper.toResponseDto(task,tagStrings);
    }
}
