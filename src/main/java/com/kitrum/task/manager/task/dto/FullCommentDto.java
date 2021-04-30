package com.kitrum.task.manager.task.dto;

import com.kitrum.task.manager.task.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FullCommentDto {

    private String id;
    private String taskId;
    private String authorId;
    private String text;
    private Date created;

    public static FullCommentDto of(Comment comment) {
        return new FullCommentDto(
                comment.getId(),
                comment.getTask() != null ? comment.getTask().getId() : null,
                comment.getAuthor() != null ? comment.getAuthor().getId() : null,
                comment.getText(),
                new Date(comment.getCreated().toInstant().toEpochMilli())
        );
    }
}
