package com.yurdm.qualifications.model.users;

import com.yurdm.qualifications.model.knowledge.Competency;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "user_id")
@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends User {
    @Column(name = "course")
    private int course;

    @ManyToMany
    @JoinTable(
            name = "student_competencies",
            joinColumns = @JoinColumn(name = "student"),
            inverseJoinColumns = @JoinColumn(name = "competency"))
    private List<Competency> competencies;
}
