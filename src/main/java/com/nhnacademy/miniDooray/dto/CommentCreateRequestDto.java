package com.nhnacademy.miniDooray.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequestDto {
    private String userId;
    private String content;
}
