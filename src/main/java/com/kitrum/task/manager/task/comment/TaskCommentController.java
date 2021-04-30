package com.kitrum.task.manager.task.controller;

import com.kitrum.task.manager.exception.ResourceNotFoundException;
import com.kitrum.task.manager.task.dto.FullCommentDto;
import com.kitrum.task.manager.task.dto.NewCommentBody;
import com.kitrum.task.manager.task.service.TaskCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks/{taskId}/comments")
public class TaskCommentController {

    private final TaskCommentService taskCommentService;

    public TaskCommentController(TaskCommentService taskCommentService) {
        this.taskCommentService = taskCommentService;
    }

    @PostMapping
    public ResponseEntity<FullCommentDto> addComment(@PathVariable String taskId,
                                                     @RequestBody NewCommentBody commentBody)
            throws ResourceNotFoundException {

        FullCommentDto comment = taskCommentService.addComment(taskId, commentBody);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id)
            throws ResourceNotFoundException {

        taskCommentService.deleteComment(id);

        return ResponseEntity.noContent()
                .build();
    }
}
