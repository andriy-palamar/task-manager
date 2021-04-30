package com.kitrum.task.manager.task.file;

import com.kitrum.task.manager.task.Task;
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
@Table(name = "task_file_tb")
public class TaskFile {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "created")
    private OffsetDateTime created;

    @PrePersist
    public void prePersist() {
        if (this.created == null) {
            this.created = OffsetDateTime.now();
        }
    }
}
