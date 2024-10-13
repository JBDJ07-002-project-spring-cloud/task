package com.nhnacademy.miniDooray.service.impl;

import com.nhnacademy.miniDooray.config.mapper.TaskMapper;
import com.nhnacademy.miniDooray.dto.task.TaskDetailResponseDto;
import com.nhnacademy.miniDooray.dto.task.TaskModifyRequestDto;
import com.nhnacademy.miniDooray.dto.task.TaskRegisterRequestDto;
import com.nhnacademy.miniDooray.dto.task.TaskResponseDto;
import com.nhnacademy.miniDooray.entity.*;
import com.nhnacademy.miniDooray.repository.ProjectTagRepository;
import com.nhnacademy.miniDooray.repository.TaskQueryDslRepository;
import com.nhnacademy.miniDooray.stubs.task.QueryDslRepositoryStub;
import com.nhnacademy.miniDooray.repository.TaskRepository;
import com.nhnacademy.miniDooray.service.TaskService;
import com.nhnacademy.miniDooray.stubs.task.CommentRepositoryStub;
import com.nhnacademy.miniDooray.stubs.task.CommentResponseDtoStub;
import com.nhnacademy.miniDooray.stubs.task.CommentStub;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectTagRepository projectTagRepository;
    private final TaskQueryDslRepository taskQueryDslRepository;

    private final CommentRepositoryStub commentRepositoryStub;
    private final QueryDslRepositoryStub queryDslRepositoryStub;

    private static final String TAG_DELIMITER = ", ";

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

    @Override
    @Transactional
    public void resgisterTask(long projectId, TaskRegisterRequestDto requestDto) {

        Project project = queryDslRepositoryStub.findProjectById(projectId);
        if (project == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid project ID");
        }

        // TODO: user가 해당 멤버가 아니라면? (이건 근데 필터에서 처리해줄 것 같음)
        User user = queryDslRepositoryStub.findUserByName(requestDto.userName());

        //TODO : 나중에 JPA로 바꿔둘것
        Milestone milestone = queryDslRepositoryStub.findMilestoneByName(projectId, requestDto.milestone());
        Task task = new Task(
                project,
                milestone,
                user,
                requestDto.taskName(),
                requestDto.content(),
                LocalDateTime.now());

        saveTaskWithTags(task, projectId, requestDto.taskTags());

    }

    @Override
    @Transactional
    public void modifyTask(long projectId, long taskId, TaskModifyRequestDto requestDto) {
        Project project = queryDslRepositoryStub.findProjectById(projectId);
        Task task = taskRepository.findByProjectIdAndId(projectId, taskId);
        //TODO : 나중에 JPA로 바꿔둘것
        Milestone milestone = queryDslRepositoryStub.findMilestoneByName(projectId, requestDto.milestone());

        if (project == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid project ID");
        } else if(milestone == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid milestone");
        } else if(task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid task ID");
        } else if(requestDto.taskName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task name must be between 1 and 50 characters");
        }

        taskQueryDslRepository.updateTask(projectId, taskId, milestone, requestDto);
        modifyTags(projectId, taskId, requestDto.taskTags(),task);

    }

    @Override
    @Transactional
    public void deleteTask(long projectId, long taskId) {

        Project project = queryDslRepositoryStub.findProjectById(projectId);
        Task task = taskRepository.findByProjectIdAndId(projectId, taskId);
        if (project == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid project ID");
        } else if(task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid task ID");
        }

        queryDslRepositoryStub.deleteCommentsByTaskId(taskId);
        projectTagRepository.deleteAllByTaskId(taskId);
        taskRepository.deleteByIdAndProjectId(projectId,taskId);
    }

    private void modifyTags(long projectId, long taskId, String taskTags, Task task){
        List<ProjectTag> existTagList = projectTagRepository.findTagByTaskId(taskId);
        List<String> newTagList = Arrays.asList(taskTags.split(TAG_DELIMITER));

        List<ProjectTag> removeTagList = existTagList.stream()
                .filter(tag -> !newTagList.contains(tag.getTag().getTagName()))
                .toList();

        for (ProjectTag tag : removeTagList) {
            projectTagRepository.delete(tag);
        }

        for (String tagName : newTagList) {
            if (!tagName.isEmpty() &&
                    existTagList.stream().noneMatch(tag -> tag.getTag().getTagName().equals(tagName))) {

                Tag tag = queryDslRepositoryStub.findTagByProjectIdAndTagName(projectId, tagName);

                if(Objects.nonNull(tag)){
                    ProjectTag newProjectTag = new ProjectTag(
                            tag,
                            task
                    );
                    projectTagRepository.save(newProjectTag);
                }
            }
        }
    }

    private void saveTaskWithTags(Task task, long projectId, String taskTags) {
        try {
            taskRepository.save(task);
            String[] tags = taskTags.split(TAG_DELIMITER);

            for(String tagName : tags){
                if(!tagName.isEmpty()){
                    ProjectTag projectTag = new ProjectTag(
                            queryDslRepositoryStub.findTagByProjectIdAndTagName(projectId, tagName),
                            task
                    );
                    projectTagRepository.save(projectTag);
                }
            }

        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Task Save Failed");
        }
    }

    private boolean isExistedProject(Long projectId){
        return true;
    }

    private TaskResponseDto convertToTaskResponseDto(Task task) {
        String tagStrings = getTagNamesByTaskId(task.getId());
        return TaskMapper.toResponseDto(task,tagStrings);
    }

    private TaskDetailResponseDto convertToTaskDetailResponseDto(Task task) {
        String tagStrings = getTagNamesByTaskId(task.getId());
        List<CommentStub> commentList = commentRepositoryStub.findByTaskId(task.getId());
        List<CommentResponseDtoStub> commentResponseDtoStubList = new ArrayList<>();

        // TODO : Stub
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
