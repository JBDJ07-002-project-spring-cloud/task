package com.nhnacademy.miniDooray.controller;

import com.nhnacademy.miniDooray.dto.InviteMemberRequest;
import com.nhnacademy.miniDooray.dto.StatusResponse;
import com.nhnacademy.miniDooray.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectMemberController {
    private final ProjectMemberService projectMemberService;

    //4. 프로젝트 멤버 초대
    @PostMapping("/{projectId}/invite")
    public ResponseEntity<StatusResponse> inviteMember(@PathVariable long projectId,
                                                       @RequestBody InviteMemberRequest inviteMemberRequest) {
        projectMemberService.inviteMember(projectId, inviteMemberRequest.getUserIds());
        StatusResponse statusResponse = new StatusResponse(200, "OK");
        return ResponseEntity.ok(statusResponse);
    }
}
