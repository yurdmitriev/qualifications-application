package com.yurdm.qualifications.service;

import com.yurdm.qualifications.model.content.Vacancy;
import com.yurdm.qualifications.repository.VacanciesRepository;
import com.yurdm.qualifications.util.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VacancyService {
    private final VacanciesRepository repository;

    @Autowired
    public VacancyService(VacanciesRepository repository) {
        this.repository = repository;
    }

    public PagedResponse<Vacancy> listAll(PageRequest pageRequest) {
        var page = repository.findAllByPublished(true, pageRequest);

        return PagedResponse.<Vacancy>builder()
                .page(pageRequest.getPageNumber() + 1)
                .total(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .items(page.toList())
                .build();
    }

    public Optional<Vacancy> getById(long id) {
        return repository.findByPublishedAndId(true, id);
    }
}
