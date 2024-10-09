package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "project_members")
public class ProjectMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_member_id")
    private String id;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private Project project;

    @NotNull
    private Role memberRole;

    enum Role {
        ADMIN, MEMBER
    }
}
