package com.hackton.case3.domain;

import com.hackton.case3.domain.enums.Priority;
import com.hackton.case3.domain.enums.Stage;
import com.hackton.case3.domain.enums.State;
import com.hackton.case3.domain.enums.Type;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.EnumType.STRING;

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
    @Setter
    private Task parent;

    @OneToMany(mappedBy = "parent")
    private Set<Task> children = new HashSet<>();

    @Enumerated(STRING)
    @Setter
    private State state;

    public Task(String text, Employee employee, Project project, State state, Stage stage, Priority priority, Type type) {
        this.text = text;
        this.employee = employee;
        this.project = project;
        this.state = state;
        this.stage = stage;
        this.priority = priority;
        this.type = type;
    }

    @Enumerated(STRING)
    @Setter
    private Stage stage;

    @Enumerated(STRING)
    private Priority priority;

    @Enumerated(STRING)
    private Type type;

    @Setter
    private OffsetDateTime startDate;
}
