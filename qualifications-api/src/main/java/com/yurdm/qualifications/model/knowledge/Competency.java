package com.yurdm.qualifications.model.knowledge;

import com.yurdm.qualifications.model.users.Student;
import com.yurdm.qualifications.model.users.Lecturer;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "competencies")
@Data
public class Competency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "parent")
    private Competency parentCompetency;

    @OneToMany(mappedBy = "id")
    private List<Competency> microCompetencies;

    @ManyToMany(mappedBy = "competencies", fetch = FetchType.LAZY)
    private List<Student> students;

    @ManyToMany(mappedBy = "competencies", fetch = FetchType.LAZY)
    private List<Lecturer> lecturers;
}
