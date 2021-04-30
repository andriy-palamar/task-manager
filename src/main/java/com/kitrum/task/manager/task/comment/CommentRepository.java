package com.kitrum.task.manager.task.repository;

import com.kitrum.task.manager.task.Task;
import com.kitrum.task.manager.task.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findAllByTask(Task task);
}
