package com.kitrum.task.manager.task.service;

import com.kitrum.task.manager.exception.ResourceNotFoundException;
import com.kitrum.task.manager.task.Task;
import com.kitrum.task.manager.task.TaskRepository;
import com.kitrum.task.manager.task.dto.FullCommentDto;
import com.kitrum.task.manager.task.dto.NewCommentBody;
import com.kitrum.task.manager.task.model.Comment;
import com.kitrum.task.manager.task.repository.CommentRepository;
import com.kitrum.task.manager.user.User;
import com.kitrum.task.manager.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskCommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskCommentService(CommentRepository commentRepository,
                              TaskRepository taskRepository,
                              UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public FullCommentDto addComment(String taskId, NewCommentBody commentBody)
            throws ResourceNotFoundException {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", taskId));
        User author = userRepository.findById(commentBody.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author", commentBody.getAuthorId()));

        Comment comment = new Comment();
        comment.setId(UUID.randomUUID().toString());
        comment.setTask(task);
        comment.setAuthor(author);
        comment.setText(commentBody.getText());

        Comment savedComment = commentRepository.save(comment);

        return FullCommentDto.of(savedComment);
    }

    public void deleteComment(String id)
            throws ResourceNotFoundException {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", id));
        commentRepository.delete(comment);
    }
}
