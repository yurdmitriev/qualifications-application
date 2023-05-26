package com.yurdm.qualifications.controller;

import com.yurdm.qualifications.model.content.Event;
import com.yurdm.qualifications.model.content.Vacancy;
import com.yurdm.qualifications.model.content.dto.ContentPublisherDTO;
import com.yurdm.qualifications.service.EventService;
import com.yurdm.qualifications.util.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService service;

    @Autowired
    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Event>> listPublishedEvents(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(service.listPublished(PageRequest.of(Math.max(page - 1, 0), size)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<PagedResponse<Event>> listAllEvents(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(service.listAll(PageRequest.of(Math.max(page - 1, 0), size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getPublishedEvent(@PathVariable long id) {
        return ResponseEntity.of(service.getPublishedById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'COMPANY')")
    @PostMapping("/{id}/published")
    public ResponseEntity<Event> setPublished(@PathVariable long id, @RequestBody ContentPublisherDTO publisherDTO) {
        try {
            return ResponseEntity.ok(service.setPublished(id, publisherDTO.isPublished()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'COMPANY')")
    @DeleteMapping("{id}")
    public void deleteEvent(@PathVariable long id) {
        service.deleteById(id);
    }
}
