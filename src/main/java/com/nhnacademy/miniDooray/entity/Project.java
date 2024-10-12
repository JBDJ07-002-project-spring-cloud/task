package com.nhnacademy.miniDooray.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Setter
    private String projectName;

    @NotNull
    @Setter
    private ProjectStatus projectStatus;

    public enum ProjectStatus {
        ACTIVE,  // 활성화 상태
        SLEEP,     // 휴면 상태
        END   // 종료 상태
    }
}