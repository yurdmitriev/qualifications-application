package com.yurdm.qualifications.model.users;

import com.yurdm.qualifications.model.knowledge.Competency;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Table(name = "lecturers")
@PrimaryKeyJoinColumn(name = "user_id")
@Data
@EqualsAndHashCode(callSuper = true)
public class Lecturer extends User {
    @Column(name = "qualification")
    private String qualification;

    @Column(name = "department")
    private String department;

    @ManyToOne
    @JoinColumn(name = "verified_by")
    private Admin verifiedBy;

    @ManyToMany
    @JoinTable(name = "lecturer_competencies", joinColumns = @JoinColumn(name = "lecturer"), inverseJoinColumns = @JoinColumn(name = "competency"))
    private List<Competency> competencies;
}
