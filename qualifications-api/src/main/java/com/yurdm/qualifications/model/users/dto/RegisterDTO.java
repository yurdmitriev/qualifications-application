package com.yurdm.qualifications.model.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yurdm.qualifications.model.users.*;
import com.yurdm.qualifications.model.users.exception.PasswordsMatchException;
import com.yurdm.qualifications.security.SecurityRole;
import lombok.Data;

@Data
public class RegisterDTO {
    @JsonProperty(value = "role", required = true)
    private SecurityRole role;

    @JsonProperty(value = "email", required = true)
    private String email;

    @JsonProperty(value = "password", required = true)
    private String password;

    @JsonProperty(value = "firstName", required = true)
    private String firstName;

    @JsonProperty(value = "lastName", required = true)
    private String lastName;

    @JsonProperty("patronymic")
    private String patronymic;

    @JsonProperty(value = "passwordConfirm", required = true)
    private String passwordConfirm;

    private CompanyDTO company;

    public Company getCompany() {
        return company.convert();
    }
}
