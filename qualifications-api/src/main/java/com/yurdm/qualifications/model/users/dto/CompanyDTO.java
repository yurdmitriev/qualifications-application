package com.yurdm.qualifications.model.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yurdm.qualifications.model.users.Company;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
public class CompanyDTO {
    @JsonProperty("id")
    private long id;

    @JsonProperty(value = "title", required = true)
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("url")
    private String url;

//    @JsonProperty("logo")
//    private String logo;

    public Company convert() {
        Company company = new Company();

        company.setId(getId());
        company.setTitle(getTitle());
        company.setDescription(getDescription());
//        company.setLogo(getLogo());
        company.setUrl(getUrl());

        return company;
    }
}
