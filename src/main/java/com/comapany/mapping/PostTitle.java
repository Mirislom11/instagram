package com.comapany.mapping;

import lombok.Data;

@Data
public class PostTitle {
    private String title;

    public PostTitle(String title) {
        this.title = title;
    }
}
