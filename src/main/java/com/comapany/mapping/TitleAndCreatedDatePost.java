package com.comapany.mapping;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TitleAndCreatedDatePost {
    private String title;
    private LocalDate createdDate;

    public TitleAndCreatedDatePost(String title, LocalDate createdDate) {
        this.title = title;
        this.createdDate = createdDate;
    }
}
