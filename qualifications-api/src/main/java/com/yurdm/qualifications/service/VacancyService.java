package com.yurdm.qualifications.service;

import com.yurdm.qualifications.model.content.Vacancy;
import com.yurdm.qualifications.repository.VacanciesRepository;
import com.yurdm.qualifications.util.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class VacancyService {
    private final VacanciesRepository repository;

    @Autowired
    public VacancyService(VacanciesRepository repository) {
        this.repository = repository;
    }

    public PagedResponse<Vacancy> listPublished(PageRequest pageRequest) {
        var page = repository.findAllByPublished(true, pageRequest);

        return PagedResponse.<Vacancy>builder()
                .page(pageRequest.getPageNumber() + 1)
                .total(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .items(page.toList())
                .build();
    }

    public PagedResponse<Vacancy> listAll(PageRequest pageRequest) {
        var page = repository.findAll(pageRequest);

        return PagedResponse.<Vacancy>builder()
                .page(pageRequest.getPageNumber() + 1)
                .total(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .items(page.toList())
                .build();
    }

    public Vacancy setPublished(long id, boolean state) {
        var vacancy = repository.findById(id).orElseThrow();
        vacancy.setPublished(state);
        if (state) {
            vacancy.setPublishDate(Date.from(Instant.now()));
        } else {
            vacancy.setPublishDate(null);
        }
        return repository.save(vacancy);
    }

    public Optional<Vacancy> getById(long id) {
        return repository.findByPublishedAndId(true, id);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
