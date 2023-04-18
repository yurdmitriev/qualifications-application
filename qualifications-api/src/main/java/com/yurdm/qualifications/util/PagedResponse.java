package com.yurdm.qualifications.util;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PagedResponse<T> {
    private List<T> items;
    private long page;
    private long total;
    private long totalPages;
}
