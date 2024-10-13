package com.nhnacademy.miniDooray.controller;

import com.nhnacademy.miniDooray.dto.project.ProjectCreateRequest;
import com.nhnacademy.miniDooray.dto.project.ProjectUpdateRequest;
import com.nhnacademy.miniDooray.dto.message.MessageResponseDto;
import com.nhnacademy.miniDooray.entity.Project;
import com.nhnacademy.miniDooray.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    //1. 프로젝트 등록
    @PostMapping
    public ResponseEntity<MessageResponseDto> create(@RequestBody ProjectCreateRequest projectCreateRequest) {
        projectService.create(projectCreateRequest.getProjectName(), projectCreateRequest.getUserId());
        MessageResponseDto statusResponse = new MessageResponseDto(200, "OK");
        return ResponseEntity.ok(statusResponse);
    }


    //2. 프로젝트 조회 -> 추후 작성 예정


    //3. 프로젝트 상태 수정
    @PatchMapping("/{projectId}")
    public ResponseEntity<MessageResponseDto> update(@PathVariable long projectId,
                                                     @RequestBody ProjectUpdateRequest projectUpdateRequest) {
        projectService.update(projectId, projectUpdateRequest.getProjectStatus());
        MessageResponseDto statusResponse = new MessageResponseDto(200, "프로젝트 상태 수정 완료");
        return ResponseEntity.ok(statusResponse);
    }


    //5. 자신이 속한 프로젝트 리스트 조회(임시 경로)
    @GetMapping("/{userId}")
    public ResponseEntity<List<Project>> getProjects(@PathVariable long userId) {
        List<Project> projects = projectService.getProjects(userId);
        return ResponseEntity.ok().body(projects);
    }


}
