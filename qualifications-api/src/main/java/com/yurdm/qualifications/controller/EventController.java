package com.yurdm.qualifications.controller;

import com.yurdm.qualifications.model.content.Event;
import com.yurdm.qualifications.service.EventService;
import com.yurdm.qualifications.util.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService service;

    @Autowired
    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Event>> listVacancies(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(service.listPublished(PageRequest.of(Math.max(page - 1, 0), size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getVacancy(@PathVariable long id) {
        return ResponseEntity.of(service.getPublishedById(id));
    }
}
