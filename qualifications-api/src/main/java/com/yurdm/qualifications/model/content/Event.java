package com.yurdm.qualifications.model.content;

import com.yurdm.qualifications.model.knowledge.Competency;
import com.yurdm.qualifications.model.users.Company;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "summary", columnDefinition = "TEXT")
    private String summary;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;

    @Column(name = "published", nullable = false)
    private boolean published = false;

    @ManyToMany
    @JoinTable(
            name = "event_competencies",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "competency_id")
    )
    private List<Competency> competencies;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
