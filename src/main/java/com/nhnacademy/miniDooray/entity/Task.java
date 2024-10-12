package com.nhnacademy.miniDooray.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tasks")
public class Task {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "FK_projects_TO_tasks_1"))
    private Project project;

    @ManyToOne
    @JoinColumn(name = "milestone_id", foreignKey = @ForeignKey(name = "FK_milestones_TO_tasks_1"))
    private Milestone milestone;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_users_TO_tasks_1"))
    private User user;

    @NotNull
    @Size(max = 50)
    private String taskName;

    @NotNull
    @Size(max = 255)
    private String content;

    private LocalDateTime createdAt;
}
