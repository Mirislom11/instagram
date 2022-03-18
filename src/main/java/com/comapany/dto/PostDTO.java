package com.comapany.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PostDTO {

    private Integer id;
    private String title;
    private String content;
    private LocalDate createdDate;
    private Integer profileId;

    public PostDTO() {
    }

    public PostDTO(String title, LocalDate createdDate) {
        this.title = title;
        this.createdDate = createdDate;
    }
}
