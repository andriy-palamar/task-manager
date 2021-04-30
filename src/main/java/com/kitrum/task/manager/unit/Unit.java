package com.kitrum.task.manager.unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "unit_tb")
public class Unit {

    @Id
    private String id;

    @Column(name = "name")
    private String name;
}
