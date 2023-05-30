package com.yurdm.qualifications.model.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class VacancyCreateDTO {
    @JsonProperty("id")
    private long id;

    private String title;
    private String description;
    private String summary;
    private String responsibilities;
    private Set<Long> competencies;
    private long company;
    private String url;
    private String city;
    private boolean published;
}
