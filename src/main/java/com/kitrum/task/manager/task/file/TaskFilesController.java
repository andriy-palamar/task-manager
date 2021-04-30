package com.kitrum.task.manager.task.file;

import com.kitrum.task.manager.exception.ResourceNotFoundException;
import com.kitrum.task.manager.task.dto.TaskFileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/tasks/{id}/files")
public class TaskFilesController {

    private final TaskFileService taskFileService;

    public TaskFilesController(TaskFileService taskFileService) {
        this.taskFileService = taskFileService;
    }

    @PostMapping
    public ResponseEntity<TaskFileDto> attachFileToTask(@PathVariable String id,
                                                        @RequestPart("file") MultipartFile file)
            throws ResourceNotFoundException, IOException {

        TaskFileDto taskFileDto = taskFileService.attachFile(id, file);

        return ResponseEntity.ok(taskFileDto);
    }
}
