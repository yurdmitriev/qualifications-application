package com.yurdm.qualifications.model.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContentPublisherDTO {
    @JsonProperty("published")
    private boolean published;
}
