package com.yurdm.qualifications.model.knowledge;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore
    private Competency parentCompetency;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    @JsonProperty("items")
    private List<Competency> microCompetencies;
}
