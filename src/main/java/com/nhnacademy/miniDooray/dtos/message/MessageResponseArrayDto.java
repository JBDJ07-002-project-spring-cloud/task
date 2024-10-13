package com.nhnacademy.miniDooray.dtos.message;

import java.util.List;

public record MessageResponseArrayDto(
        int statusCode,
        List<String> message) {}

