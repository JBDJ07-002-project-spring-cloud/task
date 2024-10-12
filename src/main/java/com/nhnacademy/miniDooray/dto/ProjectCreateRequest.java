package com.nhnacademy.miniDooray.dto;

import lombok.Data;

@Data
public class ProjectCreateRequest {
    String projectName;
    long userId;
}
