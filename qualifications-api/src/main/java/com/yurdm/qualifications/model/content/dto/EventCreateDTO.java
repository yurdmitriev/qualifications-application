package com.yurdm.qualifications.model.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EventCreateDTO {
    @JsonProperty("id")
    private long id;

    private String title;
    private String description;
    private String summary;
    private List<Long> competencies;
    private long company;
    private String url;
    private String city;
    private boolean published;
}
