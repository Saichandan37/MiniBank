package com.NewGen.MiniBank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class ExceptionResponse {
    LocalDateTime timeStamp;
    String message;
    String path;
    String errorCode;
}
