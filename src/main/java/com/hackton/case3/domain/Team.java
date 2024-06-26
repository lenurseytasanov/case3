package com.hackton.case3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="team")
    private Set<Project> projects = new HashSet<>();

    @OneToMany(mappedBy = "team")
    private Set<Employee> employees = new HashSet<>();
}
