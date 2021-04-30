package com.kitrum.task.manager.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewTaskBody {

    private String authorId;
    private String topic;
    private String description;
}
