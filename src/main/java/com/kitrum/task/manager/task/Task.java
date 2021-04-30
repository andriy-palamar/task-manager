package com.kitrum.task.manager.task;

import com.kitrum.task.manager.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task_tb")
public class Task {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @Column(name = "topic")
    private String topic;

    @Column(name = "description")
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    @Column(name = "created")
    private OffsetDateTime created;

    @Column(name = "updated")
    private OffsetDateTime updated;

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = TaskStatus.CREATED;
        }
        if (this.created == null) {
            this.created = OffsetDateTime.now();
            this.updated = this.created;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updated = OffsetDateTime.now();
    }
}
