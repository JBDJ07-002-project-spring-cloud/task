package com.nhnacademy.miniDooray.controller;

import com.nhnacademy.miniDooray.dtos.message.MessageResponseDto;
import com.nhnacademy.miniDooray.dtos.milestrone.MilestoneRequestDto;
import com.nhnacademy.miniDooray.dtos.task.TaskModifyRequestDto;
import com.nhnacademy.miniDooray.dtos.task.TaskRegisterRequestDto;
import com.nhnacademy.miniDooray.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class MilestoneController {

    private final MilestoneService milestoneService;

    @PostMapping("/{projectId}/milestone")
    public MessageResponseDto registerMilestone(
                                            @PathVariable String projectId,
                                            @RequestBody MilestoneRequestDto requestDto){
        long projectIdLong = parseId(projectId);
        milestoneService.resgisterMilestone(projectIdLong, requestDto);

        return new MessageResponseDto(200, "OK");
    }

    @PutMapping("/{projectId}/milestone/{milestoneId}")
    public MessageResponseDto getTaskByProjectIdAndTaskId(
            @RequestBody MilestoneRequestDto requestDto,
            @PathVariable String projectId,
            @PathVariable String milestoneId){

        long projectIdLong = parseId(projectId);
        long milestoneIdLong = parseId(milestoneId);
        milestoneService.modifyMilestone(projectIdLong, milestoneIdLong, requestDto);

        return new MessageResponseDto(200, "OK");

    }

    private long parseId(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid format");
        }
    }
}
