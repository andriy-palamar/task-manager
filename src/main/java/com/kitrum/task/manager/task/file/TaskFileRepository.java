package com.kitrum.task.manager.task.file;

import com.kitrum.task.manager.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskFileRepository extends JpaRepository<TaskFile, String> {

    List<TaskFile> findAllByTask(Task task);
}
