package com.nhnacademy.miniDooray.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tags")
public class Tag {
    @Id
//    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @ManyToOne
    @Setter
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "FK_projects_TO_tags_1"))
    private Project project;

    @NotNull
    @Setter
    @Size(max = 50)
    @Column(name = "tag_name")
    private String tagName;
}
