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
    private Project project;

    @ManyToOne
    private Milestone milestone;

    @ManyToOne
    private User user;

    @NotNull
    @Size(max = 50)
    private String taskName;

    @Comment("업무 생성 날짜")
    private LocalDateTime createdAt;
}
