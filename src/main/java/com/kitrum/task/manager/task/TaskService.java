package com.kitrum.task.manager.task;

import com.kitrum.task.manager.exception.ResourceNotFoundException;
import com.kitrum.task.manager.task.dto.FullCommentDto;
import com.kitrum.task.manager.task.dto.FullTaskDto;
import com.kitrum.task.manager.task.dto.NewTaskBody;
import com.kitrum.task.manager.task.dto.TaskDto;
import com.kitrum.task.manager.task.dto.TaskWithCommentsDto;
import com.kitrum.task.manager.task.dto.UpdateTaskBody;
import com.kitrum.task.manager.task.file.TaskFile;
import com.kitrum.task.manager.task.file.TaskFileRepository;
import com.kitrum.task.manager.task.model.Comment;
import com.kitrum.task.manager.task.repository.CommentRepository;
import com.kitrum.task.manager.user.User;
import com.kitrum.task.manager.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;
    private final TaskFileRepository taskFileRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository,
                       CommentRepository commentRepository,
                       TaskFileRepository taskFileRepository,
                       UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
        this.taskFileRepository = taskFileRepository;
        this.userRepository = userRepository;
    }

    public List<TaskDto> getTasks(String unitId, Boolean sort) {
        List<Task> tasks;
        if (unitId == null) {
            tasks = taskRepository.findAll();
        } else {
            tasks = taskRepository.findAllByAuthorUnitId(unitId);
        }

        if (sort) {
            return tasks.stream()
                    .map(TaskDto::of)
                    .sorted(Comparator.comparing(TaskDto::getCreated))
                    .collect(Collectors.toList());
        }

        return tasks.stream()
                .map(TaskDto::of)
                .collect(Collectors.toList());
    }

    public TaskWithCommentsDto getTask(String id)
            throws ResourceNotFoundException {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", id));
        List<Comment> comments = commentRepository.findAllByTask(task);
        List<FullCommentDto> commentDtos = comments.stream()
                .map(FullCommentDto::of)
                .collect(Collectors.toList());
        List<TaskFile> taskFiles = taskFileRepository.findAllByTask(task);
        List<String> fileUrls = taskFiles.stream()
                .map(TaskFile::getFileUrl)
                .collect(Collectors.toList());

        return TaskWithCommentsDto.of(task, commentDtos, fileUrls);
    }

    public FullTaskDto createTask(NewTaskBody taskBody)
            throws ResourceNotFoundException {

        User author = userRepository.findById(taskBody.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author", taskBody.getAuthorId()));
        Task task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setAuthor(author);
        task.setTopic(taskBody.getTopic());
        task.setDescription(taskBody.getDescription());

        Task savedTask = taskRepository.save(task);

        return FullTaskDto.of(savedTask);
    }

    public FullTaskDto updateTask(UpdateTaskBody taskBody)
            throws ResourceNotFoundException {

        Task task = taskRepository.findById(taskBody.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Task", taskBody.getId()));

        User assignee = userRepository.findById(taskBody.getAssigneeId())
                .orElseThrow(() -> new ResourceNotFoundException("Assignee", taskBody.getAssigneeId()));
        task.setAssignee(assignee);
        task.setStatus(taskBody.getStatus());

        Task updatedTask = taskRepository.save(task);

        return FullTaskDto.of(updatedTask);
    }
}
