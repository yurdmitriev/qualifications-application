package com.yurdm.qualifications.controller;

import com.yurdm.qualifications.model.content.Vacancy;
import com.yurdm.qualifications.model.content.dto.ContentPublisherDTO;
import com.yurdm.qualifications.model.content.dto.VacancyCreateDTO;
import com.yurdm.qualifications.model.users.Employee;
import com.yurdm.qualifications.model.users.User;
import com.yurdm.qualifications.security.SecurityRole;
import com.yurdm.qualifications.security.UserDetails;
import com.yurdm.qualifications.service.VacancyService;
import com.yurdm.qualifications.util.PagedResponse;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/vacancies")
public class VacancyController {
    private final VacancyService service;

    @Autowired
    public VacancyController(VacancyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Vacancy>> listPublishedVacancies(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(service.listPublished(PageRequest.of(Math.max(page - 1, 0), size)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<PagedResponse<Vacancy>> listAllVacancies(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(service.listAll(PageRequest.of(Math.max(page - 1, 0), size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vacancy> getVacancy(@PathVariable long id) {
        return ResponseEntity.of(service.getById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'COMPANY')")
    @PostMapping("/{id}/published")
    public ResponseEntity<Vacancy> setPublished(@PathVariable long id, @RequestBody ContentPublisherDTO publisherDTO) {
        try {
            return ResponseEntity.ok(service.setPublished(id, publisherDTO.isPublished()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'COMPANY')")
    @DeleteMapping("{id}")
    public void deleteVacancy(@PathVariable long id) {
        service.deleteById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'COMPANY')")
    @PostMapping
    public ResponseEntity<Vacancy> createVacancy(@RequestBody VacancyCreateDTO dto) {
        var user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        if (user.getRole().equals(SecurityRole.COMPANY.name()) &&
                dto.getCompany() != ((Employee) user).getCompany().getId()) {
            return ResponseEntity.status(403).build();
        }
        var vacancy = service.createVacancy(dto);
        return ResponseEntity.ok(vacancy);
    }
}
