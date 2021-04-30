package com.kitrum.task.manager.task.dto;

import com.kitrum.task.manager.task.Task;
import com.kitrum.task.manager.task.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FullTaskDto {

    private String id;
    private String authorId;
    private String assigneeId;
    private String topic;
    private String description;
    private TaskStatus status;
    private Date created;
    private Date updated;

    public static FullTaskDto of(Task task) {
        return new FullTaskDto(
                task.getId(),
                task.getAuthor() != null ? task.getAuthor().getId() : null,
                task.getAssignee() != null ? task.getAssignee().getId() : null,
                task.getTopic(),
                task.getDescription(),
                task.getStatus(),
                new Date(task.getCreated().toInstant().toEpochMilli()),
                new Date(task.getUpdated().toInstant().toEpochMilli())
        );
    }
}
