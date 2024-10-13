package com.nhnacademy.miniDooray.controller;

import com.nhnacademy.miniDooray.dto.message.MessageResponseDto;
import com.nhnacademy.miniDooray.dto.tag.TagCreateRequest;
import com.nhnacademy.miniDooray.dto.tag.TagUpdateRequest;
import com.nhnacademy.miniDooray.service.CommonService;
import com.nhnacademy.miniDooray.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/{projectId}/tag")
public class TagController {
    private final TagService tagService;
    private final CommonService commonService;

    //1. 등록
    @PostMapping
    public ResponseEntity<StatusResponse> create(@PathVariable long projectId,
                                                 @RequestBody TagCreateRequest tagCreateRequest,
                                                 @RequestHeader(value = "X-USER-ID", required = true) String userId) {
        commonService.isUserMemberOfProject(projectId, userId);
        tagService.create(projectId, tagCreateRequest.getTagName());
        MessageResponseDto statusResponse = new MessageResponseDto(200, "프로젝트의 태그 생성");
        return ResponseEntity.ok().body(statusResponse);
    }

    //2. 수정
    @PutMapping("/{tagId}")
    public ResponseEntity<StatusResponse> update(@PathVariable long projectId, @PathVariable long tagId,
                                                 @RequestBody TagUpdateRequest tagUpdateRequest) {
        tagService.update(tagId, projectId, tagUpdateRequest.getTagName());
        StatusResponse statusResponse = new StatusResponse(200, "프로젝트의 태그 수정");
        return ResponseEntity.ok().body(statusResponse);
    }

    //3. 삭제
    @DeleteMapping("/{tagId}")
    public ResponseEntity<StatusResponse> delete(@PathVariable long projectId, @PathVariable long tagId) {
        tagService.delete(projectId, tagId);
        StatusResponse statusResponse = new StatusResponse(200, "프로젝트의 태그 삭제");
        return ResponseEntity.ok().body(statusResponse);
    }
}
