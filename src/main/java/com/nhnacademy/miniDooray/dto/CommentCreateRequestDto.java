package com.nhnacademy.miniDooray.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String userId;
    private String content;
}
