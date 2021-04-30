package com.kitrum.task.manager.task.dto;

import com.kitrum.task.manager.task.TaskStatus;
import lombok.Data;

@Data
public class UpdateTaskBody {

    private String id;
    private String assigneeId;
    private TaskStatus status;
}
