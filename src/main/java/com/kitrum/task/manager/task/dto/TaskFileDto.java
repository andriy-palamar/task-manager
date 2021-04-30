package com.kitrum.task.manager.task.dto;


import com.kitrum.task.manager.task.file.TaskFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskFileDto {

    private String id;
    private String taskId;
    private String fileUrl;
    private Date created;

    public static TaskFileDto of(TaskFile taskFile) {
        return new TaskFileDto(
                taskFile.getId(),
                taskFile.getTask().getId(),
                taskFile.getFileUrl(),
                new Date(taskFile.getCreated().toInstant().toEpochMilli())
        );
    }
}
