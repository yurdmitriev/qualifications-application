package com.yurdm.qualifications.controller;

import com.yurdm.qualifications.model.content.Vacancy;
import com.yurdm.qualifications.service.VacancyService;
import com.yurdm.qualifications.util.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vacancies")
public class VacancyController {
    private final VacancyService service;

    @Autowired
    public VacancyController(VacancyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Vacancy>> listVacancies(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(service.listAll(PageRequest.of(Math.max(page - 1, 0), size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vacancy> getVacancy(@PathVariable long id) {
        return ResponseEntity.of(service.getById(id));
    }
}
