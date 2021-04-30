package com.kitrum.task.manager.task.file;

import com.kitrum.task.manager.exception.ResourceNotFoundException;
import com.kitrum.task.manager.ftp.FtpService;
import com.kitrum.task.manager.task.Task;
import com.kitrum.task.manager.task.TaskRepository;
import com.kitrum.task.manager.task.dto.TaskFileDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class TaskFileService {

    private final TaskFileRepository taskFileRepository;
    private final TaskRepository taskRepository;
    private final FtpService ftpService;

    public TaskFileService(TaskFileRepository taskFileRepository,
                           TaskRepository taskRepository,
                           FtpService ftpService) {
        this.taskFileRepository = taskFileRepository;
        this.taskRepository = taskRepository;
        this.ftpService = ftpService;
    }

    public TaskFileDto attachFile(String id, MultipartFile file)
            throws ResourceNotFoundException, IOException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", id));
        String filePath = ftpService.storeFile(file.getInputStream(), file.getOriginalFilename());

        TaskFile taskFile = new TaskFile();
        taskFile.setId(UUID.randomUUID().toString());
        taskFile.setTask(task);
        taskFile.setFileUrl(filePath);

        return TaskFileDto.of(taskFileRepository.save(taskFile));
    }
}
