package com.hackton.case3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Getter @NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private Employee employee;

    private Project project;

    private Task parent;

    @OneToMany(mappedBy = "parent")
    private Set<Task> children;

    private String state;

    private String stage;

    private Long priority;

    private String type;

    private Long estimation;
}
