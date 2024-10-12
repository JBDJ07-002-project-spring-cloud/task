package com.nhnacademy.miniDooray.service;

import com.nhnacademy.miniDooray.dtos.milestrone.MilestoneRequestDto;

public interface MilestoneService {
    void resgisterMilestone(long projectId, MilestoneRequestDto requestDto);

    void modifyMilestone(long projectId, long milestoneId, MilestoneRequestDto requestDto);

    void deleteMilestone(long projectId, long milestoneId);
}
