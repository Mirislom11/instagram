package com.comapany.mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentAndProfile {
    /*(comment id, comment content, profile id, profile name)*/
    private int commentId;
    private String content;
    private int profileId;
    private String profileName;

}
