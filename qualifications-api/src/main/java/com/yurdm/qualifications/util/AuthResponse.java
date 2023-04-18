package com.yurdm.qualifications.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AuthResponse {
    @JsonProperty("token")
    private String token;

    @JsonProperty("expires")
    private long expires;
}
