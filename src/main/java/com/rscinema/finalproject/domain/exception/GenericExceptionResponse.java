package com.rscinema.finalproject.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class GenericExceptionResponse {

    private final LocalDateTime date = LocalDateTime.now();
    private Integer statusCode;
    private String path;
    private String message;
}
