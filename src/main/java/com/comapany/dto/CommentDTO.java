package com.comapany.dto;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentDTO {
    private Integer id;
    private String content;
    private LocalDate createdDate;
    private Integer postId;
    private Integer profileId;
}
