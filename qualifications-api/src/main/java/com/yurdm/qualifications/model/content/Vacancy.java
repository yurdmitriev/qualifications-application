package com.yurdm.qualifications.model.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yurdm.qualifications.model.users.Company;
import com.yurdm.qualifications.model.knowledge.Competency;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "vacancies")
@Data
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "salary")
    private int salary;

    @Column(name = "publish_date", nullable = false)
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date publishDate;

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;

    @Column(name = "city")
    private String city;

    @Column(name = "summary")
    private String summary;

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

    @JsonProperty("micro_credentials")
    public List<Competency> getMicroCredentials() {
        var originalList = this.competencies;
        var unsorted = new HashSet<Competency>();

        for (var competency : originalList) {
            var temp = competency;
            unsorted.add(temp);

            while (temp.getParentCompetency() != null) {
                unsorted.add(temp.getParentCompetency());
                temp = temp.getParentCompetency();
            }
        }

        var result = unsorted.stream().filter(item -> item.getParentCompetency() == null).toList();

        for (var competency : result) {
            if (!competency.getMicroCompetencies().isEmpty()) {
                for (var micro : competency.getMicroCompetencies()) {
                    micro.getMicroCompetencies().removeIf(item -> !unsorted.contains(item));
                }
            }

            competency.getMicroCompetencies().removeIf(microCredential -> !unsorted.contains(microCredential));
        }

        return result;
    }
}
