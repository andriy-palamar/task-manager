package com.kitrum.task.manager.task.model;

import com.kitrum.task.manager.task.Task;
import com.kitrum.task.manager.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment_tb")
public class Comment {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "text")
    private String text;

    @Column(name = "created")
    private OffsetDateTime created;

    @PrePersist
    public void prePersist() {
        if (this.created == null) {
            this.created = OffsetDateTime.now();
        }
    }
}