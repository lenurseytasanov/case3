package com.hackton.case3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor @Getter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="team_id", nullable=true)
    @Setter
    private Team team;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=true)
    @Setter
    private Customer customer;

    @OneToMany(mappedBy = "project")
    private Set<Task> tasks = new HashSet<>();

    public Project(String name) {
        this.name = name;
    }
}
