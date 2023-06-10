package com.yurdm.qualifications.service;

import com.yurdm.qualifications.model.content.Vacancy;
import com.yurdm.qualifications.model.content.dto.VacancyCreateDTO;
import com.yurdm.qualifications.model.knowledge.Competency;
import com.yurdm.qualifications.model.users.Company;
import com.yurdm.qualifications.repository.VacanciesRepository;
import com.yurdm.qualifications.util.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VacancyService {
    private final VacanciesRepository repository;
    private final CompetencyService competencyService;
    private final CompanyService companyService;

    @Autowired
    public VacancyService(VacanciesRepository repository, CompetencyService competencyService, CompanyService companyService) {
        this.repository = repository;
        this.competencyService = competencyService;
        this.companyService = companyService;
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

    public Vacancy createVacancy(VacancyCreateDTO dto) {
        return repository.save(convertCreateDTO(dto));
    }

    public Vacancy convertCreateDTO(VacancyCreateDTO dto) {
        var vacancy = new Vacancy();
        vacancy.setTitle(dto.getTitle());
        vacancy.setDescription(dto.getDescription());
        vacancy.setSummary(dto.getSummary());
        vacancy.setResponsibilities(dto.getResponsibilities());
        vacancy.setUrl(dto.getUrl());
        vacancy.setCity(dto.getCity());
        vacancy.setPublished(dto.isPublished());

        List<Competency> competencies = competencyService.getCompetenciesByIds(dto.getCompetencies());
        Company company = companyService.findById(dto.getCompany());
        vacancy.setCompetencies(competencies);
        vacancy.setCompany(company);
        vacancy.setSalary(dto.getSalary());

        if (dto.getId() != 0) {
            vacancy.setId(dto.getId());
        }

        if (dto.isPublished()) {
            vacancy.setPublishDate(java.sql.Date.from(Instant.now()));
        }

        return vacancy;
    }
}
