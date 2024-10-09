package com.nhnacademy.miniDooray.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_id")
    private String id;

    @NotNull
    @ManyToOne
    private
    Task task;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @Size(max = 255)
    private String content;

    @NotNull
    private LocalDateTime createdAt;

}