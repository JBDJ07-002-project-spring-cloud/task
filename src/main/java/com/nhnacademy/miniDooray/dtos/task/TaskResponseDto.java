package com.nhnacademy.miniDooray.dtos.task;

import com.nhnacademy.miniDooray.entity.Tag;
import com.nhnacademy.miniDooray.entity.Task;

import java.util.List;

public record TaskResponseDto (

    String projectName,
    String content,
    String taskTags,
    String taskMilstone,
    String userName

){}
