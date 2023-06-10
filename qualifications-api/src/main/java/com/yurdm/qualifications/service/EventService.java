package com.yurdm.qualifications.service;

import com.yurdm.qualifications.model.content.Event;
import com.yurdm.qualifications.model.content.dto.EventCreateDTO;
import com.yurdm.qualifications.model.knowledge.Competency;
import com.yurdm.qualifications.model.users.Company;
import com.yurdm.qualifications.repository.EventsRepository;
import com.yurdm.qualifications.util.PagedResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventsRepository repository;
    private final CompetencyService competencyService;
    private final CompanyService companyService;

    public EventService(EventsRepository repository, CompetencyService competencyService, CompanyService companyService) {
        this.repository = repository;
        this.competencyService = competencyService;
        this.companyService = companyService;
    }

    public PagedResponse<Event> listPublished(PageRequest pageRequest) {
        var page = repository.findAllByPublished(true, pageRequest);

        return PagedResponse.<Event>builder()
                .page(pageRequest.getPageNumber() + 1)
                .total(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .items(page.toList())
                .build();
    }

    public PagedResponse<Event> listAll(PageRequest pageRequest) {
        var page = repository.findAll(pageRequest);

        return PagedResponse.<Event>builder()
                .page(pageRequest.getPageNumber() + 1)
                .total(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .items(page.toList())
                .build();
    }

    public Optional<Event> getPublishedById(long id) {
        return repository.findByPublishedAndId(true, id);
    }

    public Event setPublished(long id, boolean state) {
        var event = repository.findById(id).orElseThrow();
        event.setPublished(state);
        return repository.save(event);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }


    public Event createEvent(EventCreateDTO dto) {
        return repository.save(convertCreateDTO(dto));
    }

    public Event convertCreateDTO(EventCreateDTO dto) {
        var event = new Event();
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setSummary(dto.getSummary());
        event.setUrl(dto.getUrl());
        event.setCity(dto.getCity());
        event.setPublished(dto.isPublished());
        event.setStartDate(dto.getStartDate());

        List<Competency> competencies = competencyService.getCompetenciesByIds(dto.getCompetencies());
        Company company = companyService.findById(dto.getCompany());
        event.setCompetencies(competencies);
        event.setCompany(company);

        if (dto.getId() != 0) {
            event.setId(dto.getId());
        }

        return event;
    }
}
