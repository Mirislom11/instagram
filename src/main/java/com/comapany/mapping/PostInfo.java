package com.comapany.mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostInfo {
    private int postId;
    private int profileId;
    private LocalDate createdDate;
}
