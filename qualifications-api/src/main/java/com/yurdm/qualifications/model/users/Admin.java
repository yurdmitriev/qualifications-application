package com.yurdm.qualifications.model.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "user_id")
@Data
@EqualsAndHashCode(callSuper = true)
public class Admin extends User {
    @Column(name = "position")
    private String position;
}
