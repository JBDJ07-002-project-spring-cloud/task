package com.nhnacademy.miniDooray.dto;

import com.nhnacademy.miniDooray.entity.Project;
import lombok.Data;

@Data
public class ProjectUpdateRequest {
    Project.ProjectStatus projectStatus;
}
