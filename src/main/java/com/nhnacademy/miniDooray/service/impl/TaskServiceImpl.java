package com.nhnacademy.miniDooray.service.impl;

import com.nhnacademy.miniDooray.config.mapper.TaskMapper;
import com.nhnacademy.miniDooray.dtos.task.TaskDetailResponseDto;
import com.nhnacademy.miniDooray.dtos.task.TaskResponseDto;
import com.nhnacademy.miniDooray.entity.ProjectTag;
import com.nhnacademy.miniDooray.entity.Task;
import com.nhnacademy.miniDooray.repository.ProjectTagRepository;
import com.nhnacademy.miniDooray.repository.TaskRepository;
import com.nhnacademy.miniDooray.service.TaskService;
import com.nhnacademy.miniDooray.stubs.dtos.CommentRepositoryStub;
import com.nhnacademy.miniDooray.stubs.dtos.CommentResponseDtoStub;
import com.nhnacademy.miniDooray.stubs.dtos.CommentStub;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectTagRepository projectTagRepository;
    private final CommentRepositoryStub commentRepositoryStub;

    @Override
    public List<TaskResponseDto> getAllTasksByProjectId(long projectId) {
        List<Task> taskList = taskRepository.findByProjectId(projectId);

        return taskList.stream()
                .map(this::convertToTaskResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDetailResponseDto getTaskByProjectIdAndTaskId(long projectId, long taskId) {
        Task task = taskRepository.findByProjectIdAndId(projectId, taskId);

        if (task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid project ID or task ID");
        }

        return convertToTaskDetailResponseDto(task);
    }

    private TaskResponseDto convertToTaskResponseDto(Task task) {
        String tagStrings = getTagNamesByTaskId(task.getId());
        return TaskMapper.toResponseDto(task,tagStrings);
    }

    private TaskDetailResponseDto convertToTaskDetailResponseDto(Task task) {
        String tagStrings = getTagNamesByTaskId(task.getId());
        List<CommentStub> commentList = commentRepositoryStub.findByTaskId(task.getId());
        List<CommentResponseDtoStub> commentResponseDtoStubList = new ArrayList<>();

        for(CommentStub commentStub : commentList){
            CommentResponseDtoStub commentResponseDtoStub = new CommentResponseDtoStub(
                    commentStub.content(),
                    commentStub.userName()
            );
            commentResponseDtoStubList.add(commentResponseDtoStub);
        }

        return TaskMapper.toDetailResponseDto(task,tagStrings,commentResponseDtoStubList);
    }

    private String getTagNamesByTaskId(long taskId) {
        List<ProjectTag> tags = projectTagRepository.findTagByTaskId(taskId);
        return tags.stream()
                .map(tag -> tag.getTag().getTagName())
                .collect(Collectors.joining(", "));
    }

}
