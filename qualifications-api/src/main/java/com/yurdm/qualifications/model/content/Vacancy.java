package com.yurdm.qualifications.model.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yurdm.qualifications.model.users.Company;
import com.yurdm.qualifications.model.knowledge.Competency;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.*;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "vacancies")
@Data
public class Vacancy extends ContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "salary")
    private int salary;

    @Column(name = "publish_date")
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date publishDate;

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;

    @Column(name = "city")
    private String city;

    @Column(name = "summary", columnDefinition = "TEXT")
    private String summary;

    @Column(name = "responsibilities", columnDefinition = "TEXT")
    private String responsibilities;

    @Column(name = "url")
    private String url;

    @Column(name = "published", nullable = false)
    private boolean published = false;

    @ManyToMany
    @JoinTable(
            name = "vacancy_competencies",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "competency_id")
    )
    @JsonIgnore // TODO: ignore for read only
    private List<Competency> competencies;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}


