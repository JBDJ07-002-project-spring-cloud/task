package com.nhnacademy.miniDooray.controller;

import com.nhnacademy.miniDooray.dto.InviteMemberRequest;
import com.nhnacademy.miniDooray.dto.ProjectCreateRequest;
import com.nhnacademy.miniDooray.dto.ProjectUpdateRequest;
import com.nhnacademy.miniDooray.dto.StatusResponse;
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
    public ResponseEntity<StatusResponse> create(@RequestBody ProjectCreateRequest projectCreateRequest) {
        projectService.create(projectCreateRequest.getProjectName(), projectCreateRequest.getUserId());
        StatusResponse statusResponse = new StatusResponse(200, "OK");
        return ResponseEntity.ok(statusResponse);
    }


    //2. 프로젝트 조회 -> 추후 작성 예정


    //3. 프로젝트 상태 수정
    @PatchMapping("/{projectId}")
    public ResponseEntity<StatusResponse> update(@PathVariable String projectId,
                                                 @RequestBody ProjectUpdateRequest projectUpdateRequest) {
        return null;
    }

    //4. 프로젝트 멤버 초대
    @PostMapping("/{projectId}/invite")
    public ResponseEntity<StatusResponse> inviteMember(@PathVariable String projectId,
                                                       @RequestBody InviteMemberRequest inviteMemberRequest) {
        return null;
    }

    //5. 자신이 속한 프로젝트 리스트 조회(임시 경로)
    @GetMapping("/{userId}")
    public ResponseEntity<List<Project>> getProjects(@PathVariable String userId) {
        return null;
    }


}
