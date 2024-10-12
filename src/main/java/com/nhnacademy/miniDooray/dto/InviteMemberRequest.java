package com.nhnacademy.miniDooray.dto;

import lombok.Data;

import java.util.List;

@Data
public class InviteMemberRequest {
    List<Long> userIds;
}
