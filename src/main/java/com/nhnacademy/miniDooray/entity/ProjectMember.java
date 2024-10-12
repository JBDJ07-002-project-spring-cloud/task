package com.nhnacademy.miniDooray.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "project_members")
public class ProjectMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_member_id")
    private long id; //수정

    @NotNull
    @ManyToOne
    @Setter
    private User user;

    @NotNull
    @ManyToOne
    @Setter
    private Project project;

    @NotNull
    @Enumerated(EnumType.STRING) // 문자열로 저장 <- 열거형 0으로 저장하길래
    @Setter
    private Role memberRole;

    public enum Role {
        ADMIN, MEMBER
    }
}
