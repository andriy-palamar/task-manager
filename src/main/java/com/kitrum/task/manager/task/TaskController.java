package com.kitrum.task.manager.task;

import com.kitrum.task.manager.exception.ResourceNotFoundException;
import com.kitrum.task.manager.task.dto.FullTaskDto;
import com.kitrum.task.manager.task.dto.NewTaskBody;
import com.kitrum.task.manager.task.dto.TaskDto;
import com.kitrum.task.manager.task.dto.TaskWithCommentsDto;
import com.kitrum.task.manager.task.dto.UpdateTaskBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getTasks(@RequestParam(name = "unitId", required = false) String unitId,
                                                  @RequestParam(name = "sort", required = false, defaultValue = "false") Boolean sort) {
        List<TaskDto> tasks = taskService.getTasks(unitId, sort);

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskWithCommentsDto> getTask(@PathVariable String id)
            throws ResourceNotFoundException {

        TaskWithCommentsDto task = taskService.getTask(id);

        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<FullTaskDto> createTask(@RequestBody() NewTaskBody taskBody)
            throws ResourceNotFoundException {

        FullTaskDto task = taskService.createTask(taskBody);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(task);
    }

    @PatchMapping
    public ResponseEntity<FullTaskDto> updateTask(@RequestBody UpdateTaskBody taskBody)
            throws ResourceNotFoundException {

        FullTaskDto task = taskService.updateTask(taskBody);

        return ResponseEntity.ok(task);
    }
}
