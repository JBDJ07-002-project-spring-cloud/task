package com.nhnacademy.miniDooray.repository;

import com.nhnacademy.miniDooray.dtos.task.TaskModifyRequestDto;
import com.nhnacademy.miniDooray.entity.Milestone;
import com.nhnacademy.miniDooray.entity.QMilestone;
import com.nhnacademy.miniDooray.entity.QTask;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class MilestoneQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    @Transactional
    public void updateMilestone(long projectId, Milestone milestone) {

        queryFactory
                .update(QMilestone.milestone)
                .set(QMilestone.milestone.milestoneName, milestone.getMilestoneName())
                .where(QMilestone.milestone.id.eq(milestone.getId())
                        .and(QMilestone.milestone.project.id.eq(projectId)))
                .execute();
    }

}
