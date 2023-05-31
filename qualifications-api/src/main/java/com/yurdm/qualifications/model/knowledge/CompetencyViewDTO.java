package com.yurdm.qualifications.model.knowledge;

import lombok.Data;

@Data
public class CompetencyViewDTO {
    private long id;
    private String title;
    private CompetencyViewDTO parent;
}
