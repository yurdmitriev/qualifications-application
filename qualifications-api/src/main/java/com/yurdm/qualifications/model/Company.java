package com.yurdm.qualifications.model;

import com.yurdm.qualifications.model.users.Admin;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "companies")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "logo")
    private String logo;

    @ManyToOne
    @JoinColumn(name = "verified_by")
    private Admin verifiedBy;
}
