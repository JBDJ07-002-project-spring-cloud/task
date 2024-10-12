package com.nhnacademy.miniDooray.service;

import com.nhnacademy.miniDooray.dtos.milestrone.MilestoneRequestDto;

public interface MilestoneService {
    void resgisterMilestone(long projectId, MilestoneRequestDto requestDto);
}
