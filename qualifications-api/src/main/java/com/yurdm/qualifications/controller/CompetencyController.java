package com.yurdm.qualifications.controller;

import com.yurdm.qualifications.model.knowledge.Competency;
import com.yurdm.qualifications.service.CompetencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/competencies")
public class CompetencyController {
    private final CompetencyService service;

    public CompetencyController(CompetencyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Collection<Competency>> searchCompetency(@RequestParam("q") String query) {
        if (query != null && !query.isBlank()) {
            Set<Competency> parents = new HashSet<>();

            for (var competency : service.findByParentTitle(query)) {
                parents.addAll(competency.getMicroCompetencies());
            }

            return ResponseEntity.ok(Stream.concat(parents.stream(), service.findByTitle(query).stream())
                    .sorted(Comparator.comparingLong(Competency::getId))
                    .collect(Collectors.toCollection(LinkedHashSet::new)));
        } else {
            return ResponseEntity.ok(service.getAll());
        }
    }
}
