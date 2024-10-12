package com.nhnacademy.miniDooray.stubs.task;

import com.nhnacademy.miniDooray.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QueryDslRepositoryStub {

    private final JPAQueryFactory queryFactory;

    public Project findProjectById(Long projectId) {
        return queryFactory.selectFrom(QProject.project)
                .where(QProject.project.id.eq(projectId))
                .fetchOne();
    }

    public User findUserByName(String userName) {
        return queryFactory.selectFrom(QUser.user)
                .where(QUser.user.userName.eq(userName))
                .fetchOne();
    }

    // TODO: 나중에 JPA로 바꿔둘것
    public Milestone findMilestoneByName(Long projectId, String milestoneName){
        return queryFactory.selectFrom(QMilestone.milestone)
                .where(QMilestone.milestone.project.id.eq(projectId)
                    .and(QMilestone.milestone.milestoneName.eq(milestoneName)))
                .fetchOne();
    }

    public Tag findTagByProjectIdAndTagName(long projectId, String tagName) {
        return queryFactory.selectFrom(QTag.tag)
                .where(QTag.tag.project.id.eq(projectId)
                        .and(QTag.tag.tagName.eq(tagName)))
                .fetchOne();
    }


}
