package com.yurdm.qualifications.model.users;

import com.yurdm.qualifications.model.Company;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "user_id")
@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends User {
    @Column(name = "position")
    private String position;

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;
}
