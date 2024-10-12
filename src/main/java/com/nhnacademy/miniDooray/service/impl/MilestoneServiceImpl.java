package com.nhnacademy.miniDooray.service.impl;

import com.nhnacademy.miniDooray.dtos.milestrone.MilestoneRequestDto;
import com.nhnacademy.miniDooray.entity.Milestone;
import com.nhnacademy.miniDooray.entity.Project;
import com.nhnacademy.miniDooray.repository.MilestoneQueryDslRepository;
import com.nhnacademy.miniDooray.repository.MilestoneRepository;
import com.nhnacademy.miniDooray.service.MilestoneService;
import com.nhnacademy.miniDooray.stubs.task.QueryDslRepositoryStub;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MilestoneServiceImpl implements MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final MilestoneQueryDslRepository milestoneQueryDslRepository;

    private final QueryDslRepositoryStub queryDslRepositoryStub;

    @Override
    public void resgisterMilestone(long projectId, MilestoneRequestDto requestDto) {

        Project project = queryDslRepositoryStub.findProjectById(projectId);
        if (project == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid project ID");
        }

        boolean milestoneExists = milestoneRepository.existsByProjectAndMilestoneName(project, requestDto.milestoneName());
        if (milestoneExists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Milestone name already exists for this project");
        }

        Milestone milestone = new Milestone(
                project,
                requestDto.milestoneName()
        );

        milestoneRepository.save(milestone);

    }

    @Override
    @Transactional
    public void modifyMilestone(long projectId, long milestoneId, MilestoneRequestDto requestDto) {
        Project project = queryDslRepositoryStub.findProjectById(projectId);
        Milestone newMilestone = milestoneRepository.findByProjectIdAndMilestoneName(projectId, requestDto.milestoneName());
        Milestone prevMilestone = milestoneRepository.findByProjectIdAndId(projectId, milestoneId);

        if (project == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid project ID");
        } else if(prevMilestone == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid milestone");
        } else if(Objects.nonNull(newMilestone)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Milestone already exists");

        } else if(requestDto.milestoneName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "milestone name must be between 1 and 50 characters");
        }

        milestoneQueryDslRepository.updateMilestone(projectId, newMilestone);

    }
}
