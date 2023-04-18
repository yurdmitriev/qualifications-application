package com.yurdm.qualifications.model.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthDTO {
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
}
