package com.hackton.case3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    private Project project;

    @ManyToOne
    @JoinColumn(name="parent_task_id", nullable=true)
    private Task parent;

    @OneToMany(mappedBy = "parent")
    private Set<Task> children = new HashSet<>();

    private String state;

    private String stage;

    private Long priority;

    private String type;

    private Long estimation;
}
