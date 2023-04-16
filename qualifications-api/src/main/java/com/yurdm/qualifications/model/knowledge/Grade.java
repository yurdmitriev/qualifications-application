package com.yurdm.qualifications.model.knowledge;

import com.yurdm.qualifications.model.Company;
import com.yurdm.qualifications.model.users.Student;
import com.yurdm.qualifications.model.users.Lecturer;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student_grades")
@Data
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "lecturer")
    private Lecturer lecturer;

    @ManyToOne
    @JoinColumn(name = "competency")
    private Competency competency;

    @ManyToOne
    @JoinColumn(name = "student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;

    @Column(name = "grade")
    private int grade;
}
